package su.egorovwa.withoutchecksdriver.ui.authorisationScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.egorovwa.withoutchecksdriver.network.NetworkRepository
import su.egorovwa.withoutchecksdriver.network.dto.NetworkData
import javax.inject.Inject

const val TAG = "AuthorisationViewModel"

@HiltViewModel
class AuthorisationViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    var authorisationUiState by mutableStateOf(AuthorisationUiState())
        private set

    fun updateUiState(newUiState: AuthorisationUiState) {
        this.authorisationUiState = newUiState
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = networkRepository.login(
                    phone = authorisationUiState.phone,
                    password = authorisationUiState.password
                )
                if (result is NetworkData.AuthResponce) {
                    Log.i(TAG, "Success token ${result.token}")

                    authorisationUiState = authorisationUiState.copy(isSuccess = true)

                }
            }
            if (authorisationUiState.isSuccess){
                onSuccess()
            }
        }
    }
}
