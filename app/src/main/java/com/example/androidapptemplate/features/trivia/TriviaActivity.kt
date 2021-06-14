package com.example.androidapptemplate.features.trivia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapptemplate.databinding.TriviaActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriviaActivity : AppCompatActivity() {
    private val viewModel by viewModels<TriviaViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = TriviaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRandomTrivia.setOnClickListener {
            viewModel.getRandomTrivia()
        }
    }
}