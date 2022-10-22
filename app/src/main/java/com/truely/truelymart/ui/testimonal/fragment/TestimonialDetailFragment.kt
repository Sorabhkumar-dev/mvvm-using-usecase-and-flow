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
import androidx.navigation.fragment.navArgs
import com.truely.truelymart.R
import com.truely.truelymart.data.model.User
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.databinding.TestimonialDetailFragmentBinding
import com.truely.truelymart.ui.testimonal.viewmodel.TestimonialDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TestimonialDetailFragment : Fragment() {
    private lateinit var binding: TestimonialDetailFragmentBinding
    private val viewModel: TestimonialDetailViewModel by viewModels()
    private val args:TestimonialDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializer()
        setupObserver()
        setonClickListener()
        return binding.root
    }

    private fun setonClickListener() {
        binding.errorLayout.btnRetry.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            viewModel.getTestimonialDetail(args.testimonialId)
        }
    }

    private fun initializer() {
        binding = TestimonialDetailFragmentBinding.inflate(layoutInflater)
        viewModel.getTestimonialDetail(args.testimonialId)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.testimonialFlow.collect {
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
                            setupData(it.body)
                        }
                    }
                }
            }
        }
    }

    private fun setupData(user: User?) {
        user?.let {
            binding.txvUserShortName.text = "${user.name.firstname[0]} ${user.name.lastname[0]}"
            binding.txvUserName.text = "${user.name.firstname} ${user.name.lastname}"
            binding.txvUserEmail.text = user.email
            binding.txvUserId.text = user.username
            binding.txvUserAddress.text = getString(
                R.string.user_address,
                user.address.number,
                user.address.street,
                user.address.city,
                user.address.zipcode
            )
        }

    }
}