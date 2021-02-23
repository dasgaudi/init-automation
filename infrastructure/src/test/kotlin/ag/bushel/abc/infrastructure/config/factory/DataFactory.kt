package ag.bushel.abc.infrastructure.config.factory

import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

typealias PropMap = Map<String, Any?>
typealias ModelBuilder = DataFactory.DataBuilderContext.() -> () -> Any
typealias MapBuilder = () -> PropMap
typealias PersistResult = (Any) -> Unit

class DataFactory(
    val definitions: Map<KClass<*>, ModelBuilder>,
    val persists: Map<KClass<*>, PersistResult>,
    val finders: Map<KClass<*>, (UUID) -> Any?>
) {
    var persistableModels: MutableList<Pair<KClass<*>, Any>> = mutableListOf()

    inline fun <reified T : Any> make(noinline overrides: MapBuilder? = null): T {
        val definition = definitions[T::class] ?: throw NotImplementedError("Definition not registered for ${T::class}")

        val definedModel = definition.invoke(DataBuilderContext(this, overrides)).invoke() as T

        lateinit var model: T

        if (overrides != null) {
            // if overrides exist, copy model and add any override values
            // and return overwritten model

            val overridesMap = overrides.invoke()

            // todo: throw error if key is added to override that doesn't exist in declaredMemberProperties

            // convert model to map with any overwritten values
            val modelMap = T::class.declaredMemberProperties.map {
                if (overridesMap.containsKey(it.name)) {
                    it.name to overridesMap[it.name]
                } else {
                    it.name to it.get(definedModel)
                }
            }.toMap()

            // retrieve the data class copy function and use the reflection callBy to recreate updated model from the modelMap
            val copyMethod = T::class.memberFunctions.first { it.name == "copy" }

            model = copyMethod.callBy(
                mapOf(copyMethod.instanceParameter!! to definedModel)
                    .plus(
                        copyMethod.parameters.filterNot { it.name == null }.map { kParameter ->
                            modelMap.getValue(kParameter.name!!).let {
                                kParameter to it
                            }
                        }.toMap()
                    )
            ) as T
        } else {
            model = definedModel
        }

        return model.also {
            // add made model to list to persist later if needed
            persistableModels.add(T::class to it)
        }
    }

    inline fun <reified T : Any> make(count: Int = 1, noinline overrides: MapBuilder? = null): List<T> {
        // todo: there's a random issue with creating unique list that needs to be looked at.  likely something related to faker library itself
        return List(count) { this.make<T>(overrides) }
    }

    inline fun <reified T : Any> create(noinline overrides: MapBuilder? = null): T {
        // clear models list before making any new models
        persistableModels.clear()

        val model = this.make<T>(overrides)

        // persist all available models
        persistableModels.forEach { (key, value) ->
            val persist = persists[key] ?: error("No persistence function defined for $key")

            persist(value)
        }

        return model
    }

    inline fun <reified T : Any> create(count: Int = 1, noinline overrides: MapBuilder? = null): List<T> = List(count) { this.create<T>(overrides) }

    inline fun <reified T : Any> find(id: UUID): T? {
        val finder = finders[T::class] ?: error("No finder function defined for ${T::class}")

        return finder.invoke(id) as T?
    }

    inline fun <reified T : Any> exists(id: UUID) = this.find<T>(id) != null

    class DataBuilderContext(val factory: DataFactory, val overrides: MapBuilder? = null) {
        inline fun <reified T : Any> eval(key: String, block: () -> Any): T {
            if (overrides != null) {
                val overridesMap = overrides.invoke()

                if (overridesMap.containsKey(key)) {
                    return overridesMap[key] as T
                }
            }

            return block.invoke() as T
        }

        inline fun <reified T : Any> build() = factory.make() as T
    }
}
