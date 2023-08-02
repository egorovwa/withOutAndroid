package su.egorovwa.withoutchecksdriver.ui.fortesttodelete

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import su.egorovwa.withoutchecksdriver.datastore.TokenManager
import javax.inject.Inject

@HiltViewModel
class SuccessScreenViewModel @Inject constructor(
    val tokenManager: TokenManager
) : ViewModel() {
    var successUiState by mutableStateOf(SuccessUiState())
        private set

    init {
        readData()
    }

    private fun readData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                launch {
                    delay(1000)
                    tokenManager.getId().collectLatest { value: String? ->
                        successUiState = successUiState.copy(id = value ?: "")
                    }
                }
                launch {
                    delay(1000)
                    tokenManager.getToken().collect { token ->
                        successUiState = successUiState.copy(token = token ?: "")
                    }
                }
                launch {
                    delay(1000)
                    tokenManager.getPassword().collect { password ->
                        successUiState = successUiState.copy(password = password ?: "")
                    }
                }
                launch {
                    delay(1000)
                    tokenManager.getPhone().collect { phone ->
                        successUiState = successUiState.copy(phone = phone ?: "")
                    }
                }
            }
        }
    }
}

data class SuccessUiState(
    val password: String = "",
    val phone: String = "",
    val id: String = "",
    val token: String = ""
)