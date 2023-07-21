package su.egorovwa.withoutchecksdriver.ui.registrationScreen

import su.egorovwa.withoutchecksdriver.network.dto.NewDriverDto

data class DriverUiState(
    val phone: String = "",
    val isPhoneValid:Boolean =true,
    val currentCarString: String = "",
    val fistName: String = "",
    val isFistNameValid:Boolean =true,
    val lastName: String = "",
    val isLastNameValid:Boolean =true,
    val email: String = "",
    val isEmailNameValid:Boolean =true,
    val password: String = "",
    val isPasswordNameValid:Boolean =true,
    val errors:List<String> = listOf(),
    val isSuccess:Boolean = false,
    val id:String =""


    )
fun toDto(driverUiState: DriverUiState):NewDriverDto{
    return NewDriverDto(
        phone = driverUiState.phone,
        lastName = driverUiState.lastName,
        fistName = driverUiState.fistName,
        password = driverUiState.password,
        email = driverUiState.email,

    )
}