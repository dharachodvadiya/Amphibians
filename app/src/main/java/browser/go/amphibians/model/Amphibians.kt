package browser.go.amphibians.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Amphibians(
    val name: String,
    val type: String,
    val description: String,
    val imgSrc: String,
    val isExpanded: MutableState<Boolean> = mutableStateOf(false)
)