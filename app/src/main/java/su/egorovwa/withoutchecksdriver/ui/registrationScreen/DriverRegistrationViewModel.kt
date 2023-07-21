package su.egorovwa.withoutchecksdriver.ui.registrationScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.IOException
import su.egorovwa.withoutchecksdriver.network.NetworkRepository
import javax.inject.Inject

private const val TAG = "DriverRegistrationViewModel"

@HiltViewModel
class DriverRegistrationViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {


    var driverUiState by mutableStateOf(DriverUiState())
        private set

    fun updateUistate(newDriverUiState: DriverUiState) {
        this.driverUiState = newDriverUiState
    }

    fun register() {
        val dto = toDto(driverUiState = driverUiState)
        val json = Json.encodeToString(value = dto)
        Log.i(TAG, json )
        viewModelScope.launch {

            try {
                val response =
                    async { networkRepository.registre(toDto(driverUiState)) }.await()
                if (response.isSuccessful && response.code() == 201 && response.body() != null) {
                    val registered = response.body()
                    driverUiState =
                        driverUiState.copy(
                            isSuccess = true,
                            id = registered?.id?.toString() ?: ""
                        )
                }
            } catch (e: IOException) {
                val errors = driverUiState.errors.toMutableList()
                errors.add(e.message ?: "Uncnow Error") // TODO: както не так
                Log.e(TAG, "error: ${e.message}:${e}")


                driverUiState = driverUiState.copy(errors = errors.toList())
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