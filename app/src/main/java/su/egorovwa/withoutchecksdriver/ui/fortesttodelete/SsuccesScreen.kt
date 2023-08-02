package su.egorovwa.withoutchecksdriver.ui.fortesttodelete

import android.service.autofill.FieldClassification.Match
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import su.egorovwa.withoutchecksdriver.R
import su.egorovwa.withoutchecksdriver.navigation.NavigationDestination

object SuccessScreenDestanation:NavigationDestination{
    override val route: String = "SUCCESS_SCREEN"

    override val titleRes: Int = R.string.registration

}
@Composable
fun SuccessScreen(
    info:String,
    viewModel: SuccessScreenViewModel = hiltViewModel()
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        val successUiState = viewModel.successUiState
        Column {

            Text(text = "Получилося", style = MaterialTheme.typography.titleMedium)
            Text(text = "Id now: ${successUiState.id}")
            Text(text = "Phone now: ${successUiState.phone}")
            Text(text = "Passwword now: ${successUiState.password}")
            Text(text = "Token now: ${successUiState.token}")
        }
    }
}