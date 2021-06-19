package com.example.androidapptemplate.features.login

import android.os.Bundle
import android.view.View
import androidx.autofill.HintConstants
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.dialog.OnRetryConnectionListener
import com.example.androidapptemplate.databinding.FragmentLoginBinding
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBindings(FragmentLoginBinding::bind)
    private val viewModel by viewModels<LoginViewModel>()
    private val exceptionHandler =
        LoginExceptionHandler(fragment = this, onUnAuthorizedAction = {})
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
                doAction { viewModel.login() }
            }
        }
        exceptionHandler.setOnRetryConnectionListener(object : OnRetryConnectionListener {
            override fun onRetry() {
                doAction { viewModel.login() }
            }
        })
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        toastHelper.showToast("Please input Email: example@gmail.com & Password: 12345678")
    }

    private fun <T> doAction(action: suspend () -> T) {
        viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
            binding.progressIndicatorLayout.show()
            action.invoke()
            binding.progressIndicatorLayout.hide()
        }
    }

    private fun observeLiveData() {
        viewModel.loginSuccess.observe(viewLifecycleOwner, {
            findNavController().navigate(LoginFragmentDirections.goToHomeNavigation())
        })
    }
}