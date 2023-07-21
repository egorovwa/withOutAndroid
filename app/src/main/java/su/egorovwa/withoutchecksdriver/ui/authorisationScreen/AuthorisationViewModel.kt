package su.egorovwa.withoutchecksdriver.ui.authorisationScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorisationViewModel @Inject constructor() : ViewModel() {
    var authorisationUiState by mutableStateOf(AuthorisationUiState())
        private set

    fun updateUiState(newUiState: AuthorisationUiState) {
        this.authorisationUiState = newUiState
    }
}
