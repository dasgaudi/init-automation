package ag.bushel.abc.adapter.persistence.repository

import org.springframework.data.repository.CrudRepository
import java.util.*

abstract class BaseRepository<M, E> {
    abstract val dao: CrudRepository<E, UUID>

    abstract val convertToEntity: (M) -> E
    abstract val convertToModel: (E) -> M

    fun findAll(): List<M> {
        val entityList = dao.findAll()

        return entityList.map { convertToModel(it) }
    }

    fun findById(id: UUID): M? {
        val entity = dao.findById(id)

        if (entity.isEmpty) return null

        return convertToModel(entity.get())
    }

    fun existsById(id: UUID) = dao.existsById(id)

    fun save(model: M): M {
        val entity = dao.save(
            convertToEntity(model)
        )

        return convertToModel(entity)
    }

    fun delete(id: UUID) {
        dao.deleteById(id)
    }
}
