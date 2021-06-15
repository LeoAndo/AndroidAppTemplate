package com.example.androidapptemplate.data.datasource.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_histories")
internal data class TriviaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val text: String
)