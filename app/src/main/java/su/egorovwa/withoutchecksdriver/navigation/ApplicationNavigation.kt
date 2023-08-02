package su.egorovwa.withoutchecksdriver.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import su.egorovwa.withoutchecksdriver.ui.authorisationScreen.AutchorisationScreen
import su.egorovwa.withoutchecksdriver.ui.authorisationScreen.AuthorisationDestanation
import su.egorovwa.withoutchecksdriver.ui.fortesttodelete.SuccessScreen
import su.egorovwa.withoutchecksdriver.ui.fortesttodelete.SuccessScreenDestanation
import su.egorovwa.withoutchecksdriver.ui.registrationScreen.DriverRegistrationDestanation
import su.egorovwa.withoutchecksdriver.ui.registrationScreen.DriverRegistrationScreen

@Composable
fun DriverNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navHostController, startDestination = DriverRegistrationDestanation.route) {
        composable(route = AuthorisationDestanation.route) {
            AutchorisationScreen(
                canNavigateBack = true,
                navigateUp = { navHostController.navigateUp() },
                onRegistrationLinkClick = { navHostController.navigate(DriverRegistrationDestanation.route) },
                onAuthorisationButtonClick = {},
                onSuccess = {navHostController.navigate(SuccessScreenDestanation.route)}
            )
        }
        composable(route = DriverRegistrationDestanation.route) {
            DriverRegistrationScreen(
                onAuthorisationLinkClick = { navHostController.navigate(AuthorisationDestanation.route) },
                onRegistrationButtonClick = { },
                canNavigateBack = true,
                navigateUp = { navHostController.navigateUp() },
                onSuccess = {navHostController.navigate(SuccessScreenDestanation.route)}
            )
        }
        composable(route= SuccessScreenDestanation.route){
            SuccessScreen(info = "no info")
        }
    }
}