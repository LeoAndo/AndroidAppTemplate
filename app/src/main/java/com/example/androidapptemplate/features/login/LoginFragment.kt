package com.example.androidapptemplate.features.login

import android.os.Bundle
import android.view.View
import androidx.autofill.HintConstants
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentLoginBinding
import com.example.androidapptemplate.helper.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBindings(FragmentLoginBinding::bind)
    private val viewModel by viewModels<LoginViewModel>()

    @Inject
    internal lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.viewModel = viewModel
            it.emailAddress.setAutofillHints(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS)
            it.password.setAutofillHints(HintConstants.AUTOFILL_HINT_PASSWORD)
            it.emailAddress.apply {
                doAfterTextChanged {
                    viewModel.inputTextChanged()
                }
            }
            it.password.apply {
                doAfterTextChanged { viewModel.inputTextChanged() }
            }
            it.login.setOnClickListener { viewModel.login() }
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.loginSuccess.observe(viewLifecycleOwner, {
            findNavController().navigate(LoginFragmentDirections.goToHomeAction())
        })
        viewModel.invalidError.observe(viewLifecycleOwner, {
            toastHelper.showToast(it)
        })
    }
}