package su.egorovwa.withoutchecksdriver.ui.authorisationScreen

import android.view.textclassifier.TextLinks
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import su.egorovwa.withoutchecksdriver.R
import su.egorovwa.withoutchecksdriver.navigation.NavigationDestination
import su.egorovwa.withoutchecksdriver.ui.uiutils.AuthorisationTopBar
import su.egorovwa.withoutchecksdriver.ui.uiutils.InputFiled
import su.egorovwa.withoutchecksdriver.ui.uiutils.SimpleTextLink
import su.egorovwa.withoutchecksdriver.validators.isPhoneNumberValid

object AuthorisationDestanation : NavigationDestination {
    override val route = "AUTHORISATION"
    override val titleRes = R.string.authorisation_screen_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutchorisationScreen(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    viewModel: AuthorisationViewModel = hiltViewModel(),
    onRegistrationLinkClick: () -> Unit,
    onAuthorisationButtonClick: () -> Unit,
    onSuccess: () -> Unit

) {
    var authorisationUiState = viewModel.authorisationUiState
    Scaffold(topBar = {
        AuthorisationTopBar(
            title = stringResource(id = AuthorisationDestanation.titleRes),
            canNavigateBack = canNavigateBack,
            navigateUp = navigateUp,
        )
    }) {
        AuthorisationScreenBody(
            authorisationUiState = authorisationUiState,
            onRegistrationLinkClick = onRegistrationLinkClick,
            onAuthorisationButtonClick = {
                viewModel.login(onSuccess)
            },
            onValueChenge = { viewModel.updateUiState(it) },
            isFailed = authorisationUiState.isFailed,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun AuthorisationScreenBody(
    modifier: Modifier = Modifier,
    authorisationUiState: AuthorisationUiState,
    onRegistrationLinkClick: () -> Unit,
    onAuthorisationButtonClick: () -> Unit,
    onValueChenge: (AuthorisationUiState) -> Unit,
    isFailed: Boolean,
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.authorisation_screen_title),
            style = MaterialTheme.typography.headlineMedium
        )
        InputFiled(
            curentValue = authorisationUiState.phone,
            onValueChange = { onValueChenge(authorisationUiState.copy(phone = it)) },
            isValid = { isPhoneNumberValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            lable = stringResource(R.string.phone_number)
        )
        InputFiled(
            curentValue = authorisationUiState.password,
            onValueChange = { onValueChenge(authorisationUiState.copy(password = it)) },
            isValid = { isPhoneNumberValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            lable = stringResource(id = R.string.passwor)
        )
        SimpleTextLink(
            text = R.string.registration,
            onClick = onRegistrationLinkClick,
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 16.dp, top = 8.dp)
        )
        Button(onClick =  onAuthorisationButtonClick ) {
            Text(
                text = stringResource(id = R.string.authorisation_link_text),
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (isFailed) {
            Text(
                text = stringResource(R.string.failed_authorisation_text),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red,
                modifier = Modifier.padding(top = 24.dp, start = 8.dp, end = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun AuthorisationScreenPreview() {
    AuthorisationScreenBody(
        authorisationUiState = AuthorisationUiState(),
        onRegistrationLinkClick = {},
        onAuthorisationButtonClick = {},
        onValueChenge = {},
        isFailed = true
    )
}