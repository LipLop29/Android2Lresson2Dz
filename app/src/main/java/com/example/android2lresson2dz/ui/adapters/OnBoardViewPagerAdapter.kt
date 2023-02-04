package com.example.android2lresson2dz.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android2lresson2dz.ui.fragments.onboard.OnBoardPagingFragment
import com.example.android2lresson2dz.ui.fragments.onboard.OnBoardPagingFragment.Companion.ARG_ONBOARD_PAGE_POSITION

class OnBoardViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment = OnBoardPagingFragment().apply{
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_PAGE_POSITION, position)
        }
    }
}