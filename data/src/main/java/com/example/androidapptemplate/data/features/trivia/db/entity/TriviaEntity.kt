package com.example.androidapptemplate.data.features.trivia.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_histories")
internal data class TriviaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val text: String
)