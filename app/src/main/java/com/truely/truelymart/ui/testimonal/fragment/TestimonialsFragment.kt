package com.truely.truelymart.ui.testimonal.fragment

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
import com.truely.truelymart.databinding.TestimonalsFragmentBinding
import com.truely.truelymart.ui.adapter.TestimonialAdapter
import com.truely.truelymart.ui.interfaces.OnItemClickedListener
import com.truely.truelymart.ui.testimonal.viewmodel.TestimonialsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class TestimonialsFragment : Fragment() {
    private lateinit var binding:TestimonalsFragmentBinding
    private val viewModel :TestimonialsViewModel by viewModels()
    @Inject
    lateinit var testimonialAdapter: TestimonialAdapter

    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initialization(inflater)
        setupObserver()
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.errorLayout.btnRetry.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            viewModel.getUsers()
        }
        testimonialAdapter.onItemClickedListener  = object :OnItemClickedListener{
            override fun onItemClicked(id: String) {

            }

        }
    }

    private fun initialization(inflater: LayoutInflater) {
        binding = TestimonalsFragmentBinding.inflate(inflater)
        navController = findNavController()
        binding.rvUsers.adapter = testimonialAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.usersFlow.collect {
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
                            testimonialAdapter.updateUsers(it.body)
                        }
                    }
                }
            }
        }
    }
}