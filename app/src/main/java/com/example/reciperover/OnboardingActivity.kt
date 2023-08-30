package com.example.reciperover

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.reciperover.fragment.OnboardingPagerAdapter
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var onboardingPagerAdapter: OnboardingPagerAdapter
    //private lateinit var button : MaterialButton
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var onboardingIndicators: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContentView(R.layout.activity_onboarding)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )


        sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE)

        if(!isOnboardingCompleted()) {
            viewPager = findViewById(R.id.view_pager)
            onboardingPagerAdapter = OnboardingPagerAdapter(this)
            viewPager.adapter = onboardingPagerAdapter
            val button = findViewById<MaterialButton>(R.id.button)
            onboardingIndicators = findViewById(R.id.onboarding_indicators)
            setOnboardingIndicator()
            setCurrentOnboardingIndicators(0)


            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCurrentOnboardingIndicators(position)
                }
            })

            button.setOnClickListener {
                if (viewPager.currentItem < onboardingPagerAdapter.itemCount - 1) {
                    viewPager.currentItem = viewPager.currentItem + 1
                } else {
                    navigateToMainActivity()
                    sharedPreferences.edit().putBoolean("completed", true).apply()

                }
            }
        } else {
            navigateToMainActivity()
        }

    }

    private fun isOnboardingCompleted(): Boolean {
        return sharedPreferences.getBoolean("completed", false)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

//    private fun updateIndicators(currentIndex: Int) {
//        for (i in 0 until onboardingIndicators.childCount) {
//            val indicator = onboardingIndicators.getChildAt(i)
//            indicator.setBackgroundResource(
//                if(i == currentIndex) R.drawable.ic_dot_selected
//                else R.drawable.ic_dot_unselected
//            )
//        }
//    }

    private fun setOnboardingIndicator() {
        val indicators = arrayOfNulls<ImageView>(onboardingPagerAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8,0,8,0)
        }
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.ic_dot_unselected)
            )
            indicators[i]?.layoutParams = layoutParams
            onboardingIndicators.addView(indicators[i])
        }
    }

    private fun setCurrentOnboardingIndicators(index: Int) {
        val button = findViewById<MaterialButton>(R.id.button)
        val childCount = onboardingIndicators.childCount
        for (i in 0 until childCount){
            val imageView = onboardingIndicators.getChildAt(i) as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.ic_dot_selected)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.ic_dot_unselected)
                )
            }

        }
        if (index == onboardingPagerAdapter.itemCount -1) {
            button.text = getString(R.string.second_slide_btn)
        } else {
            button.text = getString(R.string.first_slide_btn)
        }

    }

}