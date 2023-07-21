package su.egorovwa.withoutchecksdriver.ui.uiutils

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import su.egorovwa.withoutchecksdriver.R

@Composable
fun SimpleTextLink(
    onClick: () -> Unit,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        modifier = modifier.clickable { onClick() },
        color = Color.Blue,
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodySmall
    )
}

@Preview
@Composable
fun simpleTextLinkPrew() {
    SimpleTextLink(onClick = { }, text = R.string.authorisation_link_text)
}