package su.egorovwa.withoutchecksdriver.ui.registrationScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalContext
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
import su.egorovwa.withoutchecksdriver.validators.isEmailValid
import su.egorovwa.withoutchecksdriver.validators.isNameValid
import su.egorovwa.withoutchecksdriver.validators.isPasswordValid
import su.egorovwa.withoutchecksdriver.validators.isPhoneNumberValid
import androidx.lifecycle.viewmodel.compose.viewModel



object DriverRegistrationDestanation : NavigationDestination {
    override val route: String = "DRIVER_REGISTRATION"
    override val titleRes: Int = R.string.registration
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverRegistrationScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: DriverRegistrationViewModel = hiltViewModel(),
    onAuthorisationLinkClick: () -> Unit,
    onRegistrationButtonClick: () -> Unit,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {

    Scaffold(topBar = {
        AuthorisationTopBar(
            title = stringResource(DriverRegistrationDestanation.titleRes),
            canNavigateBack = canNavigateBack,
            navigateUp = { navigateUp() }
        )
    }) {
        val context = LocalContext.current
        showErrors(viewModel.driverUiState.errors, context, viewModel)
        DriverRegistrationBody(
            driverUiState = viewModel.driverUiState,
            onValueChange = { viewModel.updateUistate(it) },
            onRegistrationButtonClick = {
                viewModel.register()
                Toast.makeText(context, "you id = ${viewModel.driverUiState.id}", Toast.LENGTH_SHORT).show()
                onRegistrationButtonClick()
                                        },
            onAuthorisationLinkClick = onAuthorisationLinkClick,
            hasError = viewModel.isValid(),
            erors = viewModel.getErrors(),
            modifier = modifier.padding(it)
        )
    }
}

fun showErrors(errors: List<String>, context: Context, viewModel: DriverRegistrationViewModel) {
    Toast.makeText(context, errors.joinToString(), Toast.LENGTH_SHORT).show()
    viewModel.errorShoved()
}

@Composable
fun DriverRegistrationBody(
    modifier: Modifier = Modifier,
    driverUiState: DriverUiState,
    onValueChange: (DriverUiState) -> Unit,
    onRegistrationButtonClick: () -> Unit,
    onAuthorisationLinkClick: () -> Unit,
    hasError: Boolean,
    erors: List<String>,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()

    ) {
        Text(text = stringResource(R.string.registration), style = MaterialTheme.typography.headlineMedium)
        InputFiled(
            curentValue = driverUiState.phone,
            onValueChange = {
                onValueChange(
                    driverUiState.copy(
                        phone = it,
                        isPhoneValid = isPhoneNumberValid(it)
                    )
                )
            },
            isValid = { isPhoneNumberValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            lable = stringResource(R.string.phone_number)
        )
        InputFiled(
            curentValue = driverUiState.fistName,
            onValueChange = {
                onValueChange(
                    driverUiState.copy(
                        fistName = it,
                        isFistNameValid = isNameValid(it)
                    )
                )
            },
            isValid = { isNameValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            lable = stringResource(R.string.fistname)
        )
        InputFiled(
            curentValue = driverUiState.lastName,
            onValueChange = {
                onValueChange(
                    driverUiState.copy(
                        lastName = it,
                        isLastNameValid = isNameValid(it)
                    )
                )
            },
            isValid = { isNameValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            lable = stringResource(R.string.last_name)
        )
        InputFiled(
            curentValue = driverUiState.email,
            onValueChange = {
                onValueChange(
                    driverUiState.copy(
                        email = it,
                        isEmailNameValid = isEmailValid(it)
                    )
                )
            },
            isValid = { isEmailValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            lable = stringResource(R.string.email)
        )
        InputFiled(
            curentValue = driverUiState.password,
            onValueChange = {
                onValueChange(
                    driverUiState.copy(
                        password = it,
                        isPasswordNameValid = isPasswordValid(it)
                    )
                )
            },
            isValid = { isPasswordValid(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            lable = stringResource(R.string.passwor)
        )
        SimpleTextLink(text = R.string.authorisation_link_text, onClick = onAuthorisationLinkClick)
        if (hasError) {
            val erorsString: String =
                erors.joinToString(prefix = "", postfix = ".", separator = ",")
            Text(
                text = erorsString, style = MaterialTheme.typography.bodySmall,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, end = 16.dp)
            )
        }
        Button(onClick =  onRegistrationButtonClick) {
            Text(
                text = stringResource(R.string.registration_button_text),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview
@Composable
fun PreveiwDriverScreen() {
    // DriverRegistrationBody(onValueChange = {}, driverUiState = DriverUiState())
    DriverRegistrationBody(
        driverUiState = DriverUiState(),
        onValueChange = {},
        onRegistrationButtonClick = {},
        onAuthorisationLinkClick = {},
        hasError = true,
        erors = listOf("Пароля должна быть не меньши 4 и небольши 9", "name is mamr")
    )
}