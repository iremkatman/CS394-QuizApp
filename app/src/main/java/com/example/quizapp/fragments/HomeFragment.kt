package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.adapters.CategoryAdapter
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.models.Category
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val nickname = arguments?.getString("nickname") ?: "Guest"
        binding.tvWelcome.text = "Hi, $nickname"


        binding.tvRankingValue.text = "348"
        binding.tvPointsValue.text = "1209"


        val categoryList = listOf(
            Category(21, "Sports", 10, R.drawable.img_2),
            Category(19, "Math", 40, R.drawable.img_5),
            Category(23, "History", 30, R.drawable.img_6),
            Category(17, "Science", 20, R.drawable.img_7),
            Category(22, "Geography", 25, R.drawable.img_8),
            Category(12, "Music", 35, R.drawable.img_9),
            Category(25, "Art", 15, R.drawable.img_10),
            Category(18, "Technology", 45, R.drawable.img_11),
            Category(10, "Literature", 28, R.drawable.img_12),
            Category(30, "Physics", 50, R.drawable.img_13),
            Category(27, "Biology", 33, R.drawable.img_14),
            Category(11, "Chemistry", 29, R.drawable.img_15)
        )

        val adapter = CategoryAdapter(categoryList) { category ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToCategoryDetailFragment(category.id,category.name)
            findNavController().navigate(action)
        }
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = adapter


        val bottomNavigationView = binding.root.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController()
        bottomNavigationView.setupWithNavController(navController)

        binding.ivProfileImage.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }


        return binding.root
    }
}