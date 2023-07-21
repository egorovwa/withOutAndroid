package su.egorovwa.withoutchecksdriver.ui.authorisationScreen

data class AuthorisationUiState(
    val phone:String ="",
    val isPhoneValid:Boolean =true,
    val password:String="",
    val isPasswordValid:Boolean =true,
    val isFailed:Boolean =false
)