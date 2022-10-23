package com.truely.truelymart.ui.product.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.truely.truelymart.R
import com.truely.truelymart.databinding.RootActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    lateinit var binding: RootActivityBinding
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializer()
    }

    private fun initializer() {
        binding = RootActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
    fun isShowBottomNavigation(isShow:Boolean = false){
        binding.bottomNavigation.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}