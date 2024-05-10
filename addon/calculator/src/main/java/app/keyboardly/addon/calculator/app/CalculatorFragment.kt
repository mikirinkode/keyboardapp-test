package app.keyboardly.addon.calculator.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.keyboardly.addon.calculator.R
import app.keyboardly.addon.calculator.databinding.FragmentCalculatorBinding


class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculatorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }
}