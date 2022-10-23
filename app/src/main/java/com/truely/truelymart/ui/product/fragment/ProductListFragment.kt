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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.databinding.ProductListFragmentBinding
import com.truely.truelymart.ui.adapter.ProductAdapter
import com.truely.truelymart.ui.interfaces.OnItemClickedListener
import com.truely.truelymart.ui.product.activity.RootActivity
import com.truely.truelymart.ui.product.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private lateinit var  binding:ProductListFragmentBinding
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var navController: NavController

    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initialization(inflater)
        setupObserver()
        setOnClickListener()
        return binding.root
    }

    private fun initialization(inflater: LayoutInflater) {
        binding = ProductListFragmentBinding.inflate(inflater)
        navController = findNavController()
        (activity as RootActivity).isShowBottomNavigation(true)
        productAdapter.onItemClickedListener = object : OnItemClickedListener {
            override fun onItemClicked(id: String){
                (activity as RootActivity).isShowBottomNavigation()
                navController.navigate(
                    ProductListFragmentDirections
                        .actionProductListFragmentToProductDetailFragment(id)
                )
            }
        }
        binding.rvProducts.adapter = productAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.productFlow.collect {
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
                            productAdapter.updateProducts(it.body)
                        }
                    }
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding.errorLayout.btnRetry.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            viewModel.getProducts()
        }
    }

}