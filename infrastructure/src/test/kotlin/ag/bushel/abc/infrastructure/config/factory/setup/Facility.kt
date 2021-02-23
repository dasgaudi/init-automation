package ag.bushel.abc.infrastructure.config.factory.setup

import ag.bushel.abc.core.application.port.egress.facility.FacilityPort
import ag.bushel.abc.core.domain.model.Facility
import ag.bushel.abc.core.domain.model.Region
import ag.bushel.abc.infrastructure.config.factory.DataFactoryBuilder

fun setupFacility(
    dataFactoryBuilder: DataFactoryBuilder,
    repo: FacilityPort
) {
    with(dataFactoryBuilder) {

        define<Facility> {
            fun () = Facility(
                regionId = eval("regionId") {
                    build<Region>().id
                },
                name = faker.gameOfThrones.houses()
            )
        }

        persist<Facility> {
            repo.save(it)
        }

        find {
            repo.findById(it)
        }
    }
}
