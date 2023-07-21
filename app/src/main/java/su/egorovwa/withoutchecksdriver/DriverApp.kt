package su.egorovwa.withoutchecksdriver

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import su.egorovwa.withoutchecksdriver.navigation.DriverNavHost
@Preview
@Composable
fun DriverApp(
    navHostController: NavHostController = rememberNavController()
) {
    DriverNavHost(navHostController = navHostController)
}
