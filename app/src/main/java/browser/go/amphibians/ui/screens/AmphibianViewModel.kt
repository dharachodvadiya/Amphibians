package browser.go.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import browser.go.amphibians.model.Amphibian
import browser.go.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException



sealed interface AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibianUiState
    data object Error : AmphibianUiState
    data object Loading : AmphibianUiState
}

class AmphibianViewModel : ViewModel() {

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians(){
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                val listResult = AmphibianApi.retrofitService.getAmphibians().map {
                    Amphibian(it.name, it.type, it.description, it.img_src,  mutableStateOf(false))
                }
                AmphibianUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    fun toggleExpanded(amphibian: Amphibian) {
        amphibian.isExpanded.value = !amphibian.isExpanded.value
    }

}