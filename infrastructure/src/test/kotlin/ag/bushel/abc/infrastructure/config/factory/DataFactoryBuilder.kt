package ag.bushel.abc.infrastructure.config.factory

import io.github.serpro69.kfaker.Faker
import java.util.*
import kotlin.reflect.KClass

class DataFactoryBuilder {
    val faker: Faker = Faker()
    val definitions = mutableMapOf<KClass<*>, ModelBuilder>()
    val persists = mutableMapOf<KClass<*>, PersistResult>()
    val finders = mutableMapOf<KClass<*>, (UUID) -> Any?>()

    inline fun <reified T : Any> define(noinline block: ModelBuilder) {
        check(definitions[T::class] == null) { "Definition has already been registered for ${T::class}" }

        definitions[T::class] = block
    }

    inline fun <reified T : Any> persist(noinline block: (T) -> Unit) {
        check(persists[T::class] == null) { "Persistence has already been registered for ${T::class}" }
        @Suppress("UNCHECKED_CAST")
        persists[T::class] = block as PersistResult
    }

    inline fun <reified T : Any> find(noinline block: (UUID) -> T?) {
        check(finders[T::class] == null) { "Finder has already been registered for ${T::class}" }
        @Suppress("UNCHECKED_CAST")
        finders[T::class] = block
    }

    fun build(): DataFactory = DataFactory(definitions, persists, finders)
}
