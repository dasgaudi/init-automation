package ag.bushel.abc.adapter.web.contract

object ApiRoutes {
    object Customers {
        private const val root: String = "/customers"
        const val listCustomers: String = "$root"
        const val getCustomer: String = "$root/{id}"
        const val createCustomer: String = "$root"
        const val updateCustomer: String = "$root/{id}"
        const val deleteCustomer: String = "$root/{id}"
    }

    object Regions {
        private const val root: String = "/regions"
        const val listRegions: String = "$root"
        const val getRegion: String = "$root/{id}"
        const val createRegion: String = "$root"
        const val updateRegion: String = "$root/{id}"
        const val deleteRegion: String = "$root/{id}"
    }

    object Contracts {
        private const val root: String = "/contracts"
        const val getContract: String = "$root/{id}"
        const val listContracts: String = "$root/"
    }

    object Liveness {
        private const val root: String = "/is"
        const val live: String = "$root/live"
        const val ready: String = "$root/ready"
    }
}
