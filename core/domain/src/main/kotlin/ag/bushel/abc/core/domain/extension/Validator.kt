package ag.bushel.abc.core.domain.extension

import org.valiktor.Validator
import org.valiktor.constraints.NotNull
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import kotlin.reflect.KProperty1

fun <E> Validator<E>.Property<String?>.ifNotNull(
    block: Validator<E>.Property<String?>.() -> Validator<E>.Property<String?>
) {
    this.validate(NotNull) {
        if (it != null) {
            block.invoke(this)
        }

        true
    }
}

fun <E> Validator<E>.validateFullAddress(
    address: KProperty1<E, String?>,
    city: KProperty1<E, String?>,
    state: KProperty1<E, String?>,
    postalCode: KProperty1<E, String?>
) {
    validate(address).ifNotNull {
        isNotBlank().hasSize(min = 4, max = 50)

        // if address exists, city/state/postal code are all required
        validate(city).isNotNull()
        validate(state).isNotNull()
        validate(postalCode).isNotNull()
    }

    validate(city).isNotBlank().hasSize(min = 4, max = 50)
    validate(state).isNotBlank().hasSize(min = 2, max = 2)
    validate(postalCode).isNotBlank().hasSize(min = 3, max = 20)
}
