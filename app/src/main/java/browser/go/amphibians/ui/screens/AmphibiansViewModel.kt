package browser.go.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import browser.go.amphibians.model.Amphibians
import browser.go.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException



sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibians>) : AmphibiansUiState
    data object Error : AmphibiansUiState
    data object Loading : AmphibiansUiState
}

class AmphibiansViewModel : ViewModel() {

    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians(){
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                val listResult = AmphibiansApi.retrofitService.getAmphibians().map {
                    Amphibians(it.name, it.type, it.description, it.img_src,  mutableStateOf(false))
                }
                AmphibiansUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    fun toggleExpanded(amphibian: Amphibians) {
        amphibian.isExpanded.value = !amphibian.isExpanded.value
    }

}