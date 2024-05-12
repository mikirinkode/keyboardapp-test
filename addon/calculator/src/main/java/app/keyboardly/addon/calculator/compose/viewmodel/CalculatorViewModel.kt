package app.keyboardly.addon.calculator.compose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression

/**
 * CalculatorViewModel is a ViewModel class that holds the state of the calculator screen.
 * It is responsible for handling user input, calculating the result, and updating the preview.
 */
class CalculatorViewModel : ViewModel() {
    // Holds the current sequence of numbers and operators entered by the user.
    var numText = mutableStateOf("")

    // Holds a preview of the calculation result based on the current 'numText' value.
    var calculationResultPreview = mutableStateOf("")

    // Appends a number to the 'numText' and updates the result preview.
    fun addNumber(key: String) {
        numText.value += key
        calculationResultPreview.value = calculate(numText.value)
    }


    // Clears all input and resets the result preview.
    fun clearAllValue() {
        numText.value = ""
        calculationResultPreview.value = ""
    }

    // Removes the last character from 'numText' and updates the result preview
    fun onBackspace() {
        if (numText.value.isNotEmpty()) {
            numText.value = numText.value.dropLast(1)
            calculationResultPreview.value = calculate(numText.value)
        }
    }

    /**
     * Calculates the final result and updates the preview or triggers an error callback.
     *
     * @param onInvalidFormat is a function that will be called when the format is invalid.
     */
    fun calculateResult(onInvalidFormat: () -> Unit) {
        val finalResult = calculate(numText.value)
        if (finalResult != "") {
            calculationResultPreview.value = finalResult
            numText.value = ""
        } else {
            onInvalidFormat()
        }
    }

    /**
     * Adds a decimal point to 'numText' or triggers an error callback if invalid.
     *
     * @param onInvalidFormat is a function that will be called when the format is invalid.
     */
    fun addDot(onInvalidFormat: () -> Unit) {
        if (numText.value.isNotEmpty()) {
            if (numText.value.last() != '.') {
                numText.value += '.'
            }
        } else {
            onInvalidFormat()
        }
    }

    /**
     * Adds an operator to 'numText' or replaces the last operator if necessary.
     *
     * @param key A operator symbol to be added to the 'numText'.
     *
     */
    fun addMathSymbol(key: String, onInvalidFormat: () -> Unit) {
        // throws error if trying to add operator on empty
        if (numText.value.isNotEmpty()) {
            val lastChar = numText.value.last()
            // if the last chat is an operator, replace it with the new operator
            if (lastChar == '÷' || lastChar == '×' || lastChar == '-' || lastChar == '+') {
                numText.value = numText.value.dropLast(1).plus(key)
            } else {
                numText.value += key
            }
        } else {
            onInvalidFormat()
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
    private fun calculate(value: String): String {
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
}