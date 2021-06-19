package com.example.androidapptemplate.features.webapi.trivia.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapptemplate.databinding.SimpleListItemBinding
import com.example.androidapptemplate.domain.features.webapi.trivia.model.TriviaResult

internal class TriviaHistoryListAdapter :
    ListAdapter<TriviaResult, TriviaHistoryListAdapter.TriviaHistoryVH>(ITEM_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaHistoryVH {
        val binding =
            SimpleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TriviaHistoryVH(binding)
    }

    override fun onBindViewHolder(holder: TriviaHistoryVH, position: Int) {
        val data = getItem(position)
        holder.binding.textTitle.text = data.text
    }

    inner class TriviaHistoryVH(val binding: SimpleListItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<TriviaResult>() {
            override fun areItemsTheSame(oldItem: TriviaResult, newItem: TriviaResult): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TriviaResult, newItem: TriviaResult): Boolean =
                oldItem == newItem
        }
    }
}