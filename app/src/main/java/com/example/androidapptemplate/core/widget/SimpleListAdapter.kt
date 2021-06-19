package com.example.androidapptemplate.core.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapptemplate.databinding.SimpleListItemBinding

typealias OnItemClickListener<T> = (T) -> Unit

internal class SimpleListAdapter(
    private val dataset: List<String>,
    private val onItemClicked: OnItemClickListener<String>
) :
    RecyclerView.Adapter<SimpleListAdapter.MyVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val binding =
            SimpleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyVH(binding)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val data = dataset[position]
        holder.binding.textTitle.text = data
    }

    override fun getItemCount(): Int = dataset.size

    inner class MyVH(val binding: SimpleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(dataset[position])
                }
            }
        }
    }
}