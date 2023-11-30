package com.example.coffeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coffeapp.R
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coffeapp.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {

    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.orderFragment)
        }
    }
}