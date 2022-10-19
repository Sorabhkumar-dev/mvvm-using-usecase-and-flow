package com.truely.truelymart.ui.product.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.truely.truelymart.R
import com.truely.truelymart.data.model.ProductInfo
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.databinding.ProductDeatilFragmentBinding
import com.truely.truelymart.ui.product.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private lateinit var binding: ProductDeatilFragmentBinding
    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: ProductDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializer(inflater)
        setupObserver()
        setOnClickListener()
        return binding.root
    }

    private fun initializer(inflater: LayoutInflater) {
        binding = ProductDeatilFragmentBinding.inflate(inflater)
        viewModel.getProductInfo(args.productId)
    }

    private fun setOnClickListener() {
        binding.errorLayout.btnRetry.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            viewModel.getProductInfo(args.productId)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.productInfoFlow.collect {
                    when (it) {
                        is Result.Loading -> {
                            binding.simmerLayout.startShimmer()
                            binding.simmerLayout.visibility = View.VISIBLE
                        }
                        is Result.Error -> {
                            binding.simmerLayout.stopShimmer()
                            binding.simmerLayout.visibility = View.GONE
                            binding.errorLayout.root.visibility = View.VISIBLE
                            binding.errorLayout.txvReason.text = it.message
                        }
                        is Result.Success -> {
                            binding.simmerLayout.stopShimmer()
                            binding.simmerLayout.visibility = View.GONE
                            setupProductData(it.body)
                        }
                    }
                }
            }
        }
    }

    private fun setupProductData(productInfo: ProductInfo?) {
        productInfo?.let {
            Glide
                .with(requireContext())
                .load(productInfo.image)
                .error(R.drawable.ic_launcher)
                .into(binding.imgProduct)
            binding.txvProductCategory.text = productInfo.category
            binding.txvProductTitle.text = productInfo.title
            binding.txvProductDescription.text = productInfo.description
            binding.ratingBar.rating = productInfo.rating.rate.toFloat()
            binding.txvCount.text = getString(R.string.count,productInfo.rating.count)
            binding.txvProductPrice.text = getString(R.string.price,productInfo.price)
        }
    }
}