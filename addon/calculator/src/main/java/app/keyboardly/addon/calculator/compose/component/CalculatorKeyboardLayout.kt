package app.keyboardly.addon.calculator.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.keyboardly.addon.calculator.compose.color.BackgroundColor
import app.keyboardly.addon.calculator.compose.color.DangerColor
import app.keyboardly.addon.calculator.compose.color.PrimaryColor500
import app.keyboardly.addon.calculator.compose.color.TextColor
import app.keyboardly.addon.calculator.data.constant.CalculatorFunctionType
import app.keyboardly.addon.calculator.data.constant.CalculatorKeyType
import app.keyboardly.addon.calculator.data.model.CalculatorKeyData
import org.mariuszgromada.math.mxparser.Expression

@Composable
fun CalculatorKeyboardLayout(
    keyList: List<CalculatorKeyData>,
    commitResult: (numText: String) -> Unit,
    onBackClicked: () -> Unit,
    onInvalidFormat: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chunkedList = keyList.chunked(5)
    var numText by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .background(color = Color.White)
            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
            .height(250.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
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
                Text(
                    text = "Basic Calculator",
                    color = TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = BackgroundColor, shape = MaterialTheme.shapes.small)
                ) {
                    CompositionLocalProvider(
                        LocalTextInputService provides null
                    ) {
                        Text(
                            numText,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)

                        )
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(
                    onClick = {
                        commitResult(numText)
                        numText = ""
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
                            numText += key
                        }

                        else -> {
                            when (functionType) {
                                CalculatorFunctionType.BACKSPACE -> {
                                    numText = numText.dropLast(1)
                                }

                                CalculatorFunctionType.ALL_CLEAR -> {
                                    numText = ""
                                }

                                CalculatorFunctionType.EQUAL -> {
                                    val finalResult = calculate(numText)
                                    if (finalResult != "") {
                                        numText = finalResult
                                    } else {
                                        onInvalidFormat()
                                    }
                                }

                                CalculatorFunctionType.DOT -> {
                                    if (numText.last() != '.') {
                                        numText += key
                                    }
                                }

                                else -> {
                                    if (numText.last() == '÷' || numText.last() == '×' || numText.last() == '-' || numText.last() == '+') {
                                        numText = numText.dropLast(1).plus(key)
                                    } else {
                                        numText += key
                                    }
                                }
                            }
                        }
                    }
                }, modifier = Modifier)
            }
        }
    }
}

/**
 * This function calculates the result of a mathematical expression represented as a string.
 *
 * @param value A string that represents the mathematical expression to be calculated.
 * The expression can contain numbers and the operators '+', '-', '×', '÷'.
 *
 * @return A string that represents the result of the calculation.
 * If the result is a whole number, it is returned without decimal places.
 * If the result is not a number (NaN), an empty string is returned.
 *
 * @sample
 * val result = calculate("5×2")
 * println(result)  // Output: "10"
 */
fun calculate(value: String): String {
    var temp = value.replace(Regex("÷"), "/")
    temp = temp.replace(Regex("×"), "*")

    val calculationResult = Expression(temp).calculate().toString()
    return if (calculationResult == "NaN") {
        ""
    } else {
        val formattedResult = calculationResult.toDouble()
        if (formattedResult % 1 == 0.0) {
            formattedResult.toInt().toString()
        } else {
            calculationResult
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
    )
}