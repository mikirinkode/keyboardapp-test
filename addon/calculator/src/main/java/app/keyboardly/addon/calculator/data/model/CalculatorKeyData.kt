package app.keyboardly.addon.calculator.data.model

import app.keyboardly.addon.calculator.R
import app.keyboardly.addon.calculator.data.constant.CalculatorFunctionType
import app.keyboardly.addon.calculator.data.constant.CalculatorKeyType

data class CalculatorKeyData(
    val key: String,
    val keyType: CalculatorKeyType,
    val functionType: CalculatorFunctionType? = null,
    val keyIcon: Int? = null
) {
    companion object {
        /**
         * ORDER in GRID VIEW:
         * SEVEN * EIGHT * NINE * BACKSPACE_FUNCTION  * MULTIPLY_FUNCTION
         * FOUR * FIVE * SIX * BRACKET_LEFT  * SUBTRACT_FUNCTION
         * ONE * TWO * THREE * BRACKET_RIGHT * DIVISION_FUNCTION
         * ALL_CLEAR_FUNCTION * ZERO * DOT * ADD_FUNCTION * EQUAL_FUNCTION
         */

        val keys = listOf(
            CalculatorKeyData("7", CalculatorKeyType.NUMBER),
            CalculatorKeyData("8", CalculatorKeyType.NUMBER),
            CalculatorKeyData("9", CalculatorKeyType.NUMBER),
            CalculatorKeyData("", CalculatorKeyType.FUNCTION, CalculatorFunctionType.BACKSPACE, keyIcon = R.drawable.calculator_round_backspace_24),
            CalculatorKeyData("×", CalculatorKeyType.FUNCTION, CalculatorFunctionType.MULTIPLY),

            CalculatorKeyData("4", CalculatorKeyType.NUMBER),
            CalculatorKeyData("5", CalculatorKeyType.NUMBER),
            CalculatorKeyData("6", CalculatorKeyType.NUMBER),
            CalculatorKeyData("(", CalculatorKeyType.NUMBER),
            CalculatorKeyData("÷",CalculatorKeyType.FUNCTION, CalculatorFunctionType.DIVISION),

            CalculatorKeyData("1", CalculatorKeyType.NUMBER),
            CalculatorKeyData("2", CalculatorKeyType.NUMBER),
            CalculatorKeyData("3", CalculatorKeyType.NUMBER),
            CalculatorKeyData(")",  CalculatorKeyType.NUMBER),
            CalculatorKeyData("-", CalculatorKeyType.FUNCTION, CalculatorFunctionType.SUBTRACT),

            CalculatorKeyData("CLEAR", CalculatorKeyType.FUNCTION, CalculatorFunctionType.ALL_CLEAR),
            CalculatorKeyData("0", CalculatorKeyType.NUMBER),
            CalculatorKeyData(".", CalculatorKeyType.FUNCTION),
            CalculatorKeyData("+", CalculatorKeyType.FUNCTION, CalculatorFunctionType.ADD),
            CalculatorKeyData("=", CalculatorKeyType.FUNCTION, CalculatorFunctionType.EQUAL)
        )
    }
}