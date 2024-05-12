package app.keyboardly.addon.calculator.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.keyboardly.addon.calculator.compose.color.BackgroundColor
import app.keyboardly.addon.calculator.compose.color.PrimaryColor500
import app.keyboardly.addon.calculator.compose.color.TextColor
import app.keyboardly.addon.calculator.compose.viewmodel.CalculatorViewModel
import app.keyboardly.addon.calculator.data.constant.CalculatorFunctionType
import app.keyboardly.addon.calculator.data.constant.CalculatorKeyType
import app.keyboardly.addon.calculator.data.model.CalculatorKeyData

@Composable
fun CalculatorKeyboardLayout(
    keyList: List<CalculatorKeyData>,
    commitResult: (numText: String) -> Unit,
    onBackClicked: () -> Unit,
    onInvalidFormat: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {
    // Split List into 5 items per row
    val chunkedList = keyList.chunked(5)
    val numText = viewModel.numText
    val calculationResultPreview = viewModel.calculationResultPreview

    Box(
        modifier = modifier
            .background(color = Color.White)
            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
            .height(250.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                numText.value,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                IconButton(
                    onClick = {
                        onBackClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        tint = TextColor,
                        modifier = Modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = BackgroundColor, shape = MaterialTheme.shapes.small)
                ) {
                    Text(
                        calculationResultPreview.value,
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)

                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(
                    onClick = {
                        commitResult(calculationResultPreview.value)
                        viewModel.clearAllValue()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Commit Result",
                        tint = PrimaryColor500,
                        modifier = Modifier
                            .clip(CircleShape)

                    )
                }
            }
            chunkedList.forEach {
                CalculatorRowLayout(keyRowList = it, onClick = { key, keyType, functionType ->
                    when (keyType) {
                        CalculatorKeyType.NUMBER -> {
                            viewModel.addNumber(key)
                        }

                        else -> {
                            when (functionType) {
                                CalculatorFunctionType.BACKSPACE -> {
                                    viewModel.onBackspace()
                                }

                                CalculatorFunctionType.ALL_CLEAR -> {
                                    viewModel.clearAllValue()
                                }

                                CalculatorFunctionType.EQUAL -> {
                                    viewModel.calculateResult(onInvalidFormat = {
                                        onInvalidFormat()
                                    })
                                }

                                CalculatorFunctionType.DOT -> {
                                    viewModel.addDot(onInvalidFormat = {
                                        onInvalidFormat()
                                    })
                                }

                                else -> {
                                    viewModel.addMathSymbol(key = key, onInvalidFormat = {
                                        onInvalidFormat()
                                    })
                                }
                            }
                        }
                    }
                }, modifier = Modifier)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorKeyboardLayoutPreview() {
    CalculatorKeyboardLayout(
        keyList = CalculatorKeyData.keys,
        commitResult = { _ -> },
        onBackClicked = {},
        onInvalidFormat = {},
        viewModel = CalculatorViewModel()
    )
}