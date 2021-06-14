package com.example.androidapptemplate.features.trivia.data.datasource.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TriviaTable")
data class TriviaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val text: String
)