package com.example.androidapptemplate.features.login

import android.os.Bundle
import android.view.View
import androidx.autofill.HintConstants
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.handleNetworkConnectionError
import com.example.androidapptemplate.core.util.handleUnAuthorizedError
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.FragmentLoginBinding
import com.example.androidapptemplate.domain.exception.InvalidEmailAddressException
import com.example.androidapptemplate.domain.exception.InvalidPasswordException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBindings(FragmentLoginBinding::bind)
    private val viewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.viewModel = viewModel
            it.emailAddress.setAutofillHints(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS)
            it.password.setAutofillHints(HintConstants.AUTOFILL_HINT_PASSWORD)
            it.emailAddress.apply {
                doAfterTextChanged { editable ->
                    binding.emailAddressLayout.isErrorEnabled = false
                    if (editable.toString().isEmpty()) binding.emailAddressLayout.error =
                        requireContext().getString(R.string.invalid_email_address_message)
                    viewModel.inputTextChanged()
                }
            }
            it.password.apply {
                doAfterTextChanged { editable ->
                    binding.passwordLayout.isErrorEnabled = false
                    if (editable.toString().isEmpty()) binding.passwordLayout.error =
                        requireContext().getString(R.string.invalid_password_message)
                    viewModel.inputTextChanged()
                }
            }
            it.login.setOnClickListener {
                viewModel.login()
            }
        }
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        toastHelper.showToast("Please input Email: example@gmail.com & Password: 12345678")
    }

    private fun observeLiveData() {
        viewModel.uistate.observe(viewLifecycleOwner, {
            when (it) {
                is UiState.Error -> {
                    binding.progressIndicatorLayout.hide()
                    // error handling
                    handleNetworkConnectionError(it.throwable, onRetry = {
                        toastHelper.showToast("onRetry")
                        viewModel.login()
                    })
                    handleUnAuthorizedError(it.throwable, onUnAuthorizedAction = {
                        toastHelper.showToast("onUnAuthorizedAction")
                    })
                    when (it.throwable) {
                        is InvalidEmailAddressException -> {
                            toastHelper.showToast(requireContext().getString(R.string.invalid_email_address_message))
                        }
                        is InvalidPasswordException -> {
                            toastHelper.showToast(requireContext().getString(R.string.invalid_password_message))
                        }
                    }
                }
                UiState.Loading -> {
                    binding.progressIndicatorLayout.show()
                }
                is UiState.Success -> {
                    binding.progressIndicatorLayout.hide()
                    findNavController().navigate(LoginFragmentDirections.goToHomeNavigation())
                }
            }
        })
    }
}