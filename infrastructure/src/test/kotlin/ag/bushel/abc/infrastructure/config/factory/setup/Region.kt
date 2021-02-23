package ag.bushel.abc.infrastructure.config.factory.setup

import ag.bushel.abc.core.application.port.egress.region.RegionPort
import ag.bushel.abc.core.domain.model.Region
import ag.bushel.abc.infrastructure.config.factory.DataFactoryBuilder

fun setupRegion(
    dataFactoryBuilder: DataFactoryBuilder,
    repo: RegionPort
) {
    with(dataFactoryBuilder) {

        define<Region> {
            fun () = Region(
                name = faker.gameOfThrones.cities()
            )
        }

        persist<Region> {
            repo.save(it)
        }

        find {
            repo.findById(it)
        }
    }
}
