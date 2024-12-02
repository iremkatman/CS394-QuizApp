package com.example.quizapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Binding'i başlat
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Çıkış yap butonuna tıklama işlevi
        binding.btnLogout.setOnClickListener {
            logoutUser()
        }

        return binding.root
    }

    private fun logoutUser() {
        // SharedPreferences ile oturum bilgisini temizle
        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Kullanıcıyı giriş ekranına yönlendir
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)

// alttaki kısım animasyon koyarsak porfil kısmına öyle kalacak evet!
       // binding.profileLottieAnimation.setAnimation(R.raw.profile_animation)
        // binding.profileLottieAnimation.playAnimation()

    }
}