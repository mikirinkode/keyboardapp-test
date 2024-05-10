package app.keyboardly.addon.calculator

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
        }
    }

    override fun onResume() {
        super.onResume()
    }
}