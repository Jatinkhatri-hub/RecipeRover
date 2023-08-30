package com.example.reciperover.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.reciperover.R


class OnboardingPageFragment : Fragment() {




    companion object {
        private const val ARG_TITLE = "Title"
        private const val ARG_DESCRIPTION = "Description"
        private const val ARG_IMAGE_RES_ID = "imageResId"

        fun newInstance(title: String, description: String, imageResId: Int) : OnboardingPageFragment {
            val fragment = OnboardingPageFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            args.putInt(ARG_IMAGE_RES_ID, imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_onboarding_page, container, false)
        val titleTextView: TextView = rootView.findViewById(R.id.slide_title)
        val descriptionTextView : TextView = rootView.findViewById(R.id.slide_desc)
        val fullScreen: ImageView = rootView.findViewById(R.id.slide_image)

        activity?.window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        arguments?.let {
            val title = it.getString(ARG_TITLE)
            val description = it.getString(ARG_DESCRIPTION)
            val imageResId = it.getInt(ARG_IMAGE_RES_ID)

            titleTextView.text = title
            descriptionTextView.text= description
            fullScreen.setImageResource(imageResId)

        }

        return rootView
    }

}