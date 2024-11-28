package com.example.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentLoginBinding
import com.example.quizapp.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this

        // Login işlemini gözlemleme
        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(viewModel.nickname.value!!)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Invalid login credentials!", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
