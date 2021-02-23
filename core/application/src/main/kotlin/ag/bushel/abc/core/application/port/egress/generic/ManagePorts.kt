package ag.bushel.abc.core.application.port.egress.generic

import java.util.*

fun interface SavePort<T> {
    fun save(model: T): T
}

fun interface CreatePort<T> {
    fun create(model: T): T
}

fun interface UpdatePort<T> {
    fun update(id: UUID, model: T): T
}

fun interface HardDeletePort {
    fun delete(id: UUID)
}
