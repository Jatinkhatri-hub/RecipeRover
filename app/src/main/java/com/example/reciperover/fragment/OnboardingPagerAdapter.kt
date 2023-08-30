package com.example.reciperover.fragment


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.reciperover.R

class OnboardingPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val pages = listOf(
        Page("Cook Like a Chef","Impress your friends and family with delicious homemade meals",R.drawable.ic_first_slide),
        Page("Explore Recipes", "Browse through our extensive collection of mouthwatering recipes", R.drawable.second_slide)
    )
 

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        val page = pages[position]
        return OnboardingPageFragment.newInstance(page.title, page.description, page.imageResId)
    }

}
