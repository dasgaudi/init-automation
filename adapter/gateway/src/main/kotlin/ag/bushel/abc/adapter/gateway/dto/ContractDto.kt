package ag.bushel.abc.adapter.gateway.dto
import ag.bushel.abc.adapter.gateway.serializers.UUIDSerializer
import ag.bushel.abc.core.domain.model.Contract
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ContractDto(
    @Required
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    @SerialName("contract_owner_id")
    @Serializable(with = UUIDSerializer::class)
    val contractOwnerId: UUID,
    @SerialName("total_value")
    val totalValue: Double,
    @SerialName("settled_value")
    val settledValue: Double,
    @SerialName("silos")
    val silos: Int,
    @SerialName("crop")
    val crop: String,
    @SerialName("crop_stored")
    val cropStored: Double,
    @SerialName("crop_units")
    val cropUnits: String
)

@Serializable
data class ContractListDto(
    @SerialName("contracts")
    val contracts: List<ContractDto>
)

fun ContractDto.toModel() = Contract(
    id = id,
    contractOwnerId = contractOwnerId,
    totalValue = totalValue,
    settledValue = settledValue,
    silos = silos,
    crop = crop,
    cropStored = cropStored,
    cropUnits = cropUnits
)

fun Contract.toDto() = ContractDto(
    id = id,
    contractOwnerId = contractOwnerId,
    totalValue = totalValue,
    settledValue = settledValue,
    silos = silos,
    crop = crop,
    cropStored = cropStored,
    cropUnits = cropUnits
)
