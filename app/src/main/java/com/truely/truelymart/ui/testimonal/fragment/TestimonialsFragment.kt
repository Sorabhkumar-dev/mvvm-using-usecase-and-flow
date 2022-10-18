package com.truely.truelymart.ui.testimonal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.truely.truelymart.databinding.TestimonalsFragmentBinding


class TestimonialsFragment : Fragment() {
    private lateinit var binding:TestimonalsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TestimonalsFragmentBinding.inflate(inflater)

        return binding.root
    }

}