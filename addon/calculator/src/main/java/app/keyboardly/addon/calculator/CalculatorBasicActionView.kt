package app.keyboardly.addon.calculator

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import app.keyboardly.addon.calculator.databinding.CalculatorBasicLayoutBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView

class CalculatorBasicActionView(
    dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {

    override fun onCreate() {
        val binding = CalculatorBasicLayoutBinding.inflate(getLayoutInflater())
        viewLayout = binding.root

        binding.apply {
            backButton.setOnClickListener {
                dependency.viewAddOnNavigation()
            }

            composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MaterialTheme {
                        Text("Hello from Compose!", modifier = Modifier.clickable(onClick = {
                            dependency.commitText("Hello from Compose")
                        }))
                    }
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
    }
}