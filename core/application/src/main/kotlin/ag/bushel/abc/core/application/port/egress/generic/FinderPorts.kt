package ag.bushel.abc.core.application.port.egress.generic

import java.util.*

interface FinderPort<T> : FindAllPort<T>, FindByIdPort<T>

fun interface FindAllPort<T> {
    fun findAll(): List<T>
}

fun interface FindByIdPort<T> {
    fun findById(id: UUID): T?
}
