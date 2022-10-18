package com.truely.truelymart.ui.product.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.truely.truelymart.databinding.ProductDeatilFragmentBinding


class ProductDetailFragment : Fragment() {
    private lateinit var binding:ProductDeatilFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductDeatilFragmentBinding.inflate(inflater)

        return binding.root
    }

}