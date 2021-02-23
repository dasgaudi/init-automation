package ag.bushel.abc.core.domain.validation

import ag.bushel.abc.core.domain.model.Region
import assertk.all
import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test
import org.valiktor.ConstraintViolationException

class RegionValidationTest {
    @Test
    fun region_BlankName_ShouldNotValidate() {
        assertThat {
            Region(name = "")
        }.isFailure().all {
            isInstanceOf(ConstraintViolationException::class)
        }
    }
}
