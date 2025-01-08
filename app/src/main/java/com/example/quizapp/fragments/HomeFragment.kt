package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.adapters.CategoryAdapter
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.models.Category
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter // Adapter burada tanımlı
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance() // FirebaseAuth başlatma
        firestore = FirebaseFirestore.getInstance() // Firestore başlatma

        // Kullanıcı adı ve puanları göster
        displayUserNickname()
        fetchTotalScore()

        // Adapter'i oluştur
        categoryAdapter = CategoryAdapter { category ->
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryDetailFragment(
                category.id,
                category.name
            )
            findNavController().navigate(action)
        }

        // RecyclerView yapılandırması
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = categoryAdapter

        // Listeyi adapter'e gönder
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
        categoryAdapter.submitList(categoryList)

        // BottomNavigationView yapılandırması
        val bottomNavigationView = binding.root.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController()
        bottomNavigationView.setupWithNavController(navController)

        // Profil resmine tıklama
        binding.ivProfileImage.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        return binding.root
    }

    private fun displayUserNickname() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            val nickname = email?.substringBefore("@") ?: "Guest" // '@' öncesini al
            binding.tvWelcome.text = "Hi, $nickname" // Kullanıcı adını TextView'e yaz
        } else {
            binding.tvWelcome.text = "Hi, Guest" // Kullanıcı oturum açmamışsa varsayılan mesaj
        }
    }




    //Total puanı anasayfada yazdırma
    private fun fetchTotalScore() {
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null) {
            val userDocRef = firestore.collection("Leaderboard").document(userId)
            userDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val totalScore = document.getLong("totalScore") ?: 0L
                    binding.tvPointsValue.text = totalScore.toString() // Total Score'u TextView'e yaz
                } else {
                    binding.tvPointsValue.text = "0" // Varsayılan değer
                }
            }.addOnFailureListener {
                binding.tvPointsValue.text = "0" // Hata durumunda varsayılan değer
            }
        } else {
            binding.tvPointsValue.text = "0" // Kullanıcı oturum açmamışsa varsayılan değer
        }
    }
}
