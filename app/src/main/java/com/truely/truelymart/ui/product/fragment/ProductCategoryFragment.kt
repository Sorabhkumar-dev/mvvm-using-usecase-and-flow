package com.truely.truelymart.ui.product.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.truely.truelymart.databinding.ProductCategoryFragmentBinding


class ProductCategoryFragment : Fragment() {
    private lateinit var binding:ProductCategoryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductCategoryFragmentBinding.inflate(inflater)

        return binding.root
    }
}
