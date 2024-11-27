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

        // Set nickname in the welcome message
        val nickname = arguments?.getString("nickname") ?: "Guest"
        binding.tvWelcome.text = "Hi, $nickname"

        // Set ranking and points values
        binding.tvRankingValue.text = "348"
        binding.tvPointsValue.text = "1209"

        // Create category list and set up RecyclerView
        val categoryList = listOf(
            Category(1, "Sports", 50, R.drawable.img_2),
            Category(2, "Math", 40, R.drawable.img_2),
            Category(3, "History", 30, R.drawable.img_2),
            Category(4, "Science", 20, R.drawable.img_2),
            Category(5, "Geography", 25, R.drawable.img_2),
            Category(6, "Music", 35, R.drawable.img_2),
            Category(7, "Art", 15, R.drawable.img_1),
            Category(8, "Technology", 45, R.drawable.img_2),
            Category(9, "Literature", 28, R.drawable.img_2),
            Category(10, "Physics", 50, R.drawable.img_2),
            Category(11, "Biology", 33, R.drawable.img_2),
            Category(12, "Chemistry", 29, R.drawable.img_2)
        )

        val adapter = CategoryAdapter(categoryList) { category ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToCategoryDetailFragment(category.id)
            findNavController().navigate(action)
        }
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = adapter

        // BottomNavigationView'i NavController ile bağlama
        val bottomNavigationView = binding.root.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController()
        bottomNavigationView.setupWithNavController(navController)

        return binding.root
    }
}