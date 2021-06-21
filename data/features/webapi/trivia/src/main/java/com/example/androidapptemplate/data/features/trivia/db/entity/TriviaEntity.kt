package com.example.androidapptemplate.data.features.trivia.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidapptemplate.domain.features.webapi.trivia.model.TriviaResult

@Entity(tableName = "trivia_histories")
internal data class TriviaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val text: String
)

internal fun TriviaEntity.toModel(): TriviaResult {
    return TriviaResult(id = this.id, text = this.text)
}