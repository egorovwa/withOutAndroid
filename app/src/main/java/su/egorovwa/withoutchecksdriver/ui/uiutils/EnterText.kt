package su.egorovwa.withoutchecksdriver.ui.uiutils

import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFiled(
    modifier: Modifier = Modifier
        .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 16.dp),
    curentValue: String,
    onValueChange: (String) -> Unit,
    isValid: (String) -> Boolean,
    keyboardOptions: KeyboardOptions,
    lable: String

) {
        OutlinedTextField(
            value = curentValue,

            onValueChange = {
                onValueChange(it)
            },
            keyboardOptions = keyboardOptions,
            label = { Text(text = lable) }
        )

}
@Preview
@Composable
fun InputFiledPreview() {
    InputFiled(curentValue = "", onValueChange = {}, isValid = { true } ,
        keyboardOptions = KeyboardOptions.Default, lable = "input text")
}
