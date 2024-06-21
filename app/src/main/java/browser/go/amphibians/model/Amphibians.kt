package browser.go.amphibians.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Amphibians(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val img_src: String,
    val isExpanded: MutableState<Boolean> = mutableStateOf(false)
)