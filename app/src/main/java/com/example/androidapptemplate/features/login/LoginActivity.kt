package com.example.androidapptemplate.features.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.autofill.HintConstants
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.androidapptemplate.MainActivity
import com.example.androidapptemplate.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityLoginBinding.inflate(layoutInflater).let {
            setContentView(it.root)
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
        viewModel.loginSuccess.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
        })
        viewModel.invalidError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}