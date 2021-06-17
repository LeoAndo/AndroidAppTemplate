package com.example.androidapptemplate.features.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapptemplate.R

typealias OnItemClickListener<T> = (T) -> Unit

internal class SimpleListAdapter(
    private val dataset: List<String>,
    private val onItemClicked: OnItemClickListener<String>
) :
    RecyclerView.Adapter<SimpleListAdapter.MyVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item, parent, false)
        return MyVH(itemView)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val data = dataset[position]
        holder.textTitle.text = data
    }

    override fun getItemCount(): Int = dataset.size

    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.findViewById<TextView>(R.id.text_title)!!

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(dataset[position])
                }
            }
        }
    }
}