package ag.bushel.abc.infrastructure.config.factory.setup

import ag.bushel.abc.core.application.port.egress.customer.CustomerPort
import ag.bushel.abc.core.domain.model.Customer
import ag.bushel.abc.infrastructure.config.factory.DataFactoryBuilder

fun setupCustomer(
    dataFactoryBuilder: DataFactoryBuilder,
    repo: CustomerPort
) {
    with(dataFactoryBuilder) {

        define<Customer> {
            fun () = Customer(
                name = "${faker.name.firstName()} ${faker.name.lastName()}",
                address = faker.address.streetAddress(),
                city = faker.address.city(),
                state = faker.address.stateAbbr(),
                postalCode = faker.address.postcode()
            )
        }

        persist<Customer> {
            repo.save(it)
        }

        find {
            repo.findById(it)
        }
    }
}
