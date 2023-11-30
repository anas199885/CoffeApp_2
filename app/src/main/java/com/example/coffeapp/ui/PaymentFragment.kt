package com.example.coffeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coffeapp.R
import com.example.coffeapp.databinding.FragmentPaymentBinding
import com.example.coffeapp.domail.OrderDataRemoteModel
import com.example.coffeapp.domail.PayDataRemoteModel

class PaymentFragment : Fragment() {

    private lateinit var binding : FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initListener()
    }

    private fun initListener() {
        val order = arguments?.getParcelable<OrderDataRemoteModel>("order")
        binding.btnPlaceOrder.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val pickupTime = binding.tpTime.hour.toString() + ":" + binding.tpTime.minute.toString()
            val cardType = binding.spPayCard.selectedItem.toString()
            val cardNumber = binding.etCardNumber.text.toString()
            val cardExpiryMonth = binding.etCardNumMonth.text.toString()
            val cardExpiryYear = binding.etCardNumYear.text.toString()
            val cvvNumber = binding.etCvvNum.text.toString()

            openPaymentScreen(fullName, phoneNumber)

            openCardScreen(cardType, cardNumber)

            if (cvvNumber.isNotEmpty()&& cardNumber.isNotEmpty()&& cardExpiryMonth.isNotEmpty()&&cardExpiryYear.isNotEmpty()){
                val orderData = PayDataRemoteModel(
                    fullName,
                    phoneNumber,
                    pickupTime,
                    cardType,
                    cardNumber,
                    cardExpiryMonth,
                    cardExpiryYear,
                    cvvNumber,
                    order!!
                )

                val bundle = bundleOf("Payment" to orderData)
                findNavController().navigate(R.id.resultFragment, bundle)
            }
        }
    }

    private fun openPaymentScreen(fullName: String,phoneNumber: String ) {
        if (fullName.isNotEmpty()&& phoneNumber.length==10) {
            binding.PaymentScreen.isVisible = true
        }
        if (fullName.isEmpty()){
            Toast.makeText(requireContext(),"Please Add Your Name", Toast.LENGTH_LONG).show()
        }
        if (phoneNumber.length!= 10){
            Toast.makeText(requireContext(),"Please Add phone number, phone number is not correct",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun openCardScreen(cardType: String,cardNumber: String) {
        if (cardType != "_"){
            binding.cardScreen.isVisible = true
        } else if (cardType == "_"){
            Toast.makeText(requireContext(),"Please enter card type", Toast.LENGTH_LONG).show()
        }
        if (cardNumber.length != 16){
            Toast.makeText(requireContext(),"Please make shore card number is 16", Toast.LENGTH_LONG).show()
        }
    }
}