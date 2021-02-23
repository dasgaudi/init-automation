package ag.bushel.abc.core.application.port.ingress.generic

import ag.bushel.abc.core.domain.exception.NotFoundException
import java.util.*

interface FinderUseCase<T> {
    fun findAll(): List<T>
    fun find(id: UUID): T?
}

inline fun <reified T> FinderUseCase<T>.findOrFail(id: UUID): T {
    return find(id) ?: throw NotFoundException(T::class, id)
}
