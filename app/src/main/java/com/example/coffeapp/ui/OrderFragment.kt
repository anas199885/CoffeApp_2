package com.example.coffeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coffeapp.R
import com.example.coffeapp.databinding.FragmentOrderBinding
import com.example.coffeapp.domail.OrderDataRemoteModel

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        var drinkType = CoffeType()
        var drinkSize = DrinkSize()
        var options = Options()
        if (drinkType != null && drinkSize != null && options != null)
            continueClick(drinkType,drinkSize,options)
    }

    fun CoffeType(): String {
        var drink = ""

        binding.rdCoffeeType.setOnCheckedChangeListener { _, checkedId ->
            drink = when (checkedId) {
                R.id.rb_americano -> "Americano"
                R.id.rb_cappuccino -> "Cappuccino"
                R.id.rb_latte -> "Latte"
                R.id.rb_macchiato -> "Macchiato"
                else -> ""
            }
            showDrinkSize()
        }
        return drink
    }

    fun DrinkSize(): String {
        var size = ""
        binding.rdSize.setOnCheckedChangeListener { _, checkedId ->
            size = when (checkedId) {
                R.id.smallSize -> "Small"
                R.id.mediumSize -> "Medium"
                R.id.largeSize -> "Large"
                else -> ""
            }
            showOptions()
            //
        }
        return  size
    }
    private fun Options(): MutableList<String> {
        val options : MutableList<String> = mutableListOf()
        val optionCheckBoxes = listOf(
            binding.checkBox1,
            binding.checkBox2,
            binding.checkBox3,
            binding.checkBox4,
            binding.checkBox5,
            binding.checkBox6,
            binding.checkBox7
        )

        optionCheckBoxes.forEachIndexed { index, checkBox ->
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    options.add(checkBox.text.toString())
                } else {
                    options.remove(checkBox.text.toString())
                }
            }
        }
        return options
    }

    private fun showOptions() {
        binding.options.isVisible = true
    }

    private fun showDrinkSize() {
        binding.drinkSize.isVisible = true
        binding.btnContinue.isClickable = true
    }


    private fun continueClick(drinkType: String, drinkSize: String, options: MutableList<String>) {
        binding.btnContinue.setOnClickListener {
            val order = OrderDataRemoteModel(drinkType, drinkSize, options)
            val bundle = bundleOf("order" to order)
            val resultFragment=ResultFragment()
            resultFragment.arguments = bundle
            findNavController().navigate(R.id.paymentFragment, bundle)
        }
    }
}