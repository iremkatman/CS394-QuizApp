package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentCategoryDetailBinding

class CategoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentCategoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)



        val categoryName = arguments?.getString("categoryName") ?: "Unknown Category"
        val categoryId = arguments?.getInt("categoryId") ?: 0
        val lottieAnimationView = binding.lottieAnimation

        binding.tvCategoryTitle.text = categoryName
        binding.textViewCategoryId.visibility = View.GONE


        when (categoryName) {
            "Sports" -> lottieAnimationView.setAnimation(R.raw.sports) // sports.json
             "Math" -> lottieAnimationView.setAnimation(R.raw.math)

        }

        lottieAnimationView.playAnimation()




        binding.btnEasy.setOnClickListener {
            val action =
                CategoryDetailFragmentDirections.actionCategoryDetailFragmentToQuizFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}
