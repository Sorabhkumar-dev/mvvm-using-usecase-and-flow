package com.truely.truelymart.ui.cart.fragment

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
import com.truely.truelymart.databinding.CartFragmentBinding
import com.truely.truelymart.ui.adapter.CartAdapter
import com.truely.truelymart.ui.cart.viewmodel.CartViewModel
import com.truely.truelymart.ui.interfaces.OnItemClickedListener
import com.truely.truelymart.ui.product.activity.RootActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel:CartViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding:CartFragmentBinding
    @Inject
    lateinit var cartAdapter: CartAdapter

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
        binding = CartFragmentBinding.inflate(inflater)
        navController = findNavController()
        (activity as RootActivity).isShowBottomNavigation(true)
        binding.rvCart.adapter = cartAdapter
    }

    private fun setOnClickListener() {
        binding.errorLayout.btnRetry.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            viewModel.getCartItem()
        }
        cartAdapter.onItemClickedListener = object :OnItemClickedListener{
            override fun onItemClicked(id: String) {
                (activity as RootActivity).isShowBottomNavigation()
                navController.navigate(CartFragmentDirections.actionCartFragmentToProductDetailFragment(id))
            }

        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.cartItemFlow.collect {
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
                            binding.errorLayout.root.visibility = View.GONE
                            cartAdapter.updateCart(it.body)
                        }
                    }
                }
            }
        }
    }

}