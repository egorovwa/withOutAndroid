package su.egorovwa.withoutchecksdriver.ui.registrationScreen

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
import su.egorovwa.withoutchecksdriver.data.DataBaseRepository
import su.egorovwa.withoutchecksdriver.datastore.TokenManager
import su.egorovwa.withoutchecksdriver.network.NetworkRepository
import su.egorovwa.withoutchecksdriver.network.dto.NetworkData
import javax.inject.Inject

private const val TAG = "DriverRegistrationViewModel"

@HiltViewModel
class DriverRegistrationViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val tokenManager: TokenManager
) : ViewModel() {


    var driverUiState by mutableStateOf(DriverUiState())
        private set

    fun updateUistate(newDriverUiState: DriverUiState) {
        this.driverUiState = newDriverUiState
    }

    fun register(onSuccess:()->Unit) {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val networkData = networkRepository.registre(toDto(driverUiState))
                if (networkData is NetworkData.NewDriverDto) {
                    driverUiState =
                        driverUiState.copy(id = networkData.id.toString())
                } else {
                    val eror = networkData as NetworkData.NetworkError
                    driverUiState = driverUiState.copy(errors = listOf(eror.message))
                }
                val autitification = networkRepository.login(driverUiState.phone, driverUiState.password )
                if (autitification is NetworkData.AuthResponce){
                    tokenManager.saveId(autitification.driverId.toString())
                    tokenManager.savePhone(driverUiState.phone)
                    tokenManager.savePassword(driverUiState.password)
                    tokenManager.saveToken(autitification.token)
                driverUiState = driverUiState.copy(isSuccess = true)
                }
            }
            if (driverUiState.isSuccess){
                onSuccess()
            }
        }


        Log.d(TAG, "Register button pressed")
    }

    fun isValid(): Boolean {
        return true // TODO: Сделать нормально
    }

    fun getErrors(): List<String> {
        return listOf()
    }

    fun errorShoved() {
        this.driverUiState = driverUiState.copy(errors = listOf())
    }

}