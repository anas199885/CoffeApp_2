package com.example.coffeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffeapp.databinding.FragmentResultBinding
import com.example.coffeapp.domail.PayDataRemoteModel

class ResultFragment: Fragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        val orderInfo = arguments?.getParcelable<PayDataRemoteModel>("Payment")
        binding.payment = orderInfo
        if (orderInfo?.orderInfo?.drinkOptions?.isNotEmpty() == true) {
            binding.tvOrder.text = "A ${orderInfo.orderInfo.drinkSize} Of ${orderInfo.orderInfo.drinkName}, With ${orderInfo.orderInfo.drinkOptions}"
        }else{
            binding.tvOrder.text = "A ${orderInfo?.orderInfo?.drinkSize} Of ${orderInfo?.orderInfo?.drinkName}"
        }
    }
}