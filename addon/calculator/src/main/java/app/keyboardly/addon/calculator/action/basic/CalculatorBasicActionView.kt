package app.keyboardly.addon.calculator.action.basic

import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import app.keyboardly.addon.calculator.compose.component.CalculatorKeyboardLayout
import app.keyboardly.addon.calculator.data.model.CalculatorKeyData
import app.keyboardly.addon.calculator.databinding.CalculatorBasicLayoutBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView

class CalculatorBasicActionView(
    dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {

    override fun onCreate() {
        val binding = CalculatorBasicLayoutBinding.inflate(getLayoutInflater())
        viewLayout = binding.root

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    CalculatorKeyboardLayout(
                        keyList = CalculatorKeyData.keys,
                        commitResult = { numText ->
                            dependency.commitText(numText)
                        },
                        onBackClicked = {
                            dependency.viewAddOnNavigation()
                        },
                        onInvalidFormat = {
                            Toast.makeText(context, "Invalid format", Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}