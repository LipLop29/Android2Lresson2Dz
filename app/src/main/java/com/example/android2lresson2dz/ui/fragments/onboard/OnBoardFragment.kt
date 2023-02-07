package com.example.android2lresson2dz.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.android2lresson2dz.R
import com.example.android2lresson2dz.databinding.FragmentOnBoardBinding
import com.example.android2lresson2dz.ui.adapters.OnBoardViewPagerAdapter
import com.example.android2lresson2dz.utils.PreferenceHelper

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        open()
    }

    private fun initialize() = with(binding) {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        dotsIndicator.attachTo(viewPager2)
        PreferenceHelper.unit(requireContext())

    }

    private fun setupListener() = with(binding.viewPager2) {
        binding.btnSkip.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }
        binding.btnInput.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteAppFragment)
        }
        next()
    }

    private fun next() = with(binding) {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        btnSkip.isVisible = true
                        btnInput.isVisible = false
                    }
                    1 -> {
                        btnSkip.isVisible = true
                        btnInput.isVisible = false
                    }
                    2 -> {
                        btnSkip.isVisible = false
                        btnInput.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
    private fun open() {
        PreferenceHelper.safeBool = true
    }
}