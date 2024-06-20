package browser.go.amphibians.ui.screens

import android.os.Debug
import android.util.Log
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
    data class Success(val amphibianses: List<Amphibians>) : AmphibiansUiState
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
                val listResult = AmphibiansApi.retrofitService.getAmphibians()
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

}