package su.egorovwa.withoutchecksdriver.ui.uiutils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import su.egorovwa.withoutchecksdriver.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorisationTopBar(
    title: String,
    canNavigateBack:Boolean,
    modifier: Modifier = Modifier,
    navigateUp:()->Unit

) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_desc)
                    )
                }
            }
        }

    )

}