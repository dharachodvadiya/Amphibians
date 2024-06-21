package browser.go.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibianApiModel(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val img_src: String
)