package app.keyboardly.addon.calculator.compose.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.keyboardly.addon.calculator.data.constant.CalculatorFunctionType
import app.keyboardly.addon.calculator.data.constant.CalculatorKeyType
import app.keyboardly.addon.calculator.data.model.CalculatorKeyData


@Composable
fun CalculatorRowLayout(
    keyRowList: List<CalculatorKeyData>,
    onClick: (key: String, keyType: CalculatorKeyType, functionType: CalculatorFunctionType?) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        keyRowList.forEach {
            CalculatorKeyItem(
                key = it.key,
                keyIcon = it.keyIcon,
                keyType = it.keyType,
                functionType = it.functionType,
                onClick = { onClick(it.key, it.keyType, it.functionType) },
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorRowLayoutPreview() {
    val chunkedList = CalculatorKeyData.keys.chunked(5)
    CalculatorRowLayout(keyRowList = chunkedList.last(), onClick = { _, _, _ -> })
}