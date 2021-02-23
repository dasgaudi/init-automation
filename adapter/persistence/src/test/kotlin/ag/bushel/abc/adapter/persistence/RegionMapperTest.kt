package ag.bushel.abc.adapter.persistence

import ag.bushel.abc.adapter.persistence.entity.RegionEntity
import ag.bushel.abc.adapter.persistence.entity.toEntity
import ag.bushel.abc.adapter.persistence.entity.toModel
import ag.bushel.abc.core.domain.model.Region
import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.Test
import java.util.*

class RegionMapperTest {
    @Test
    fun `when Region is converted from entity to model then should map successfully`() {
        val entity = RegionEntity(id = UUID.randomUUID(), name = "New Region")

        val model = entity.toModel()

        model.run {
            assertThat(this).isNotNull()
            assertThat(id).all {
                isNotNull()
                isEqualTo(entity.id)
            }
            assertThat(name).isEqualTo(entity.name)
            assertThat(createdAt).all {
                isNotNull()
                isEqualTo(entity.createdAt)
            }
        }
    }

    @Test
    fun `when Region is converted from model to entity then should map successfully`() {
        val model = Region(name = "New Region")

        val entity = model.toEntity()

        entity.run {
            assertThat(this).isNotNull()
            assertThat(id).all {
                isNotNull()
                isEqualTo(model.id)
            }
            assertThat(name).isEqualTo(entity.name)
            assertThat(createdAt).all {
                isNotNull()
            }
        }
    }
}
