package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quizapp.R
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
            val nickname = binding.nicknameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (nickname.isNotEmpty() && password.isNotEmpty()) {
                // HomeFragment'e nickname ile geçiş
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(nickname)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}
