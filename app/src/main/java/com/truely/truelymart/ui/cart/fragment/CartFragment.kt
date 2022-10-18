package com.truely.truelymart.ui.cart.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.truely.truelymart.R
import com.truely.truelymart.databinding.CartFragmentBinding

class CartFragment : Fragment() {

    private lateinit var binding:CartFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = CartFragmentBinding.inflate(inflater)


        return binding.root
    }
}