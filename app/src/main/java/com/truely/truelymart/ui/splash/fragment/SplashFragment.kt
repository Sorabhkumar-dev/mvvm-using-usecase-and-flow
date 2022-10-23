package com.truely.truelymart.ui.splash.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.truely.truelymart.R
import com.truely.truelymart.databinding.SplashFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private lateinit var binding: SplashFragmentBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = SplashFragmentBinding.inflate(layoutInflater)
        navController = findNavController()
        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce_anim)
        binding.imgLog.animation = animation
        lifecycleScope.launch{
            delay(4000)
            navController.navigate(SplashFragmentDirections.actionSplashFragmentToProductListFragment())
        }
        return binding.root
    }
}