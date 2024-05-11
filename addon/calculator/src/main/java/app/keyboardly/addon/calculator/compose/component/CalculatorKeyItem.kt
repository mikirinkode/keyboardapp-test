package app.keyboardly.addon.calculator.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.keyboardly.addon.calculator.compose.color.BackgroundColor
import app.keyboardly.addon.calculator.compose.color.DangerColor
import app.keyboardly.addon.calculator.compose.color.PrimaryColor500
import app.keyboardly.addon.calculator.compose.color.TextColor
import app.keyboardly.addon.calculator.data.constant.CalculatorFunctionType
import app.keyboardly.addon.calculator.data.constant.CalculatorKeyType

@Composable
fun CalculatorKeyItem(
    key: String,
    keyIcon: Int?,
    keyType: CalculatorKeyType,
    functionType: CalculatorFunctionType?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(
                color = if (functionType == CalculatorFunctionType.EQUAL) PrimaryColor500 else BackgroundColor,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
    ) {
        if (keyIcon != null) {
            Icon(
                painterResource(id = keyIcon),
                contentDescription = functionType.toString(),
                tint = PrimaryColor500,
                modifier = Modifier
                    .padding(16.dp)
                    .size(18.dp)
                    .align(Alignment.Center),
            )
        } else {
            Text(
                text = key,
                fontWeight = if (keyType == CalculatorKeyType.FUNCTION && functionType != CalculatorFunctionType.ALL_CLEAR) {
                    FontWeight.Black
                } else {
                    FontWeight.Bold
                },
                color = if (keyType == CalculatorKeyType.FUNCTION) {
                    if (functionType == CalculatorFunctionType.ALL_CLEAR) {
                        DangerColor
                    } else if (functionType == CalculatorFunctionType.EQUAL) {
                        Color.White
                    } else {
                        PrimaryColor500
                    }
                } else {
                    TextColor
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorKeyItemPreview() {
    CalculatorKeyItem(
        key = "1",
        keyIcon = null,
        keyType = CalculatorKeyType.NUMBER,
        functionType = null,
        onClick = {}
    )
}