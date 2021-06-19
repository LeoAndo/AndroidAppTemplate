package com.example.androidapptemplate.domain.features.algorithm.model

import java.io.Serializable

data class AlgorithmDetailsMessage(
    val overview: String,
    val link: String? = null
) : Serializable
