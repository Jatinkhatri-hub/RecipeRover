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
 //   private var dotIndicatorViews = mutableListOf<ImageView>()

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        val page = pages[position]
        return OnboardingPageFragment.newInstance(page.title, page.description, page.imageResId)
    }

//    fun setUpDotIndicators(dotIndicatorLayout: LinearLayout) {
//        val context = dotIndicatorLayout.context
//        dotIndicatorViews.clear()
//        dotIndicatorLayout.removeAllViews()
//        for (i in pages.indices) {
//            val dotImageView = ImageView(context)
//            dotImageView.setImageResource(R.drawable.ic_dot_unselected)
//            dotIndicatorViews.add(dotImageView)
//            dotIndicatorLayout.addView(dotImageView)
//        }
//        selectDotIndicator(0)
//    }
//
//    fun selectDotIndicator(position: Int) {
//
//        for((index, dotImageView) in dotIndicatorViews.withIndex()) {
//            dotImageView.setImageResource(
//                if (index == position) R.drawable.ic_dot_selected else R.drawable.ic_dot_unselected
//            )
//        }
//
//    }
}