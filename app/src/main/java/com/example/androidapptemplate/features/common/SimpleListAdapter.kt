package com.example.androidapptemplate.features.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapptemplate.R

internal class MyAdapter(private val dataset: List<String>) : RecyclerView.Adapter<MyAdapter.MyVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent)
        return MyVH(itemView)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val data = dataset[position]

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

        }
    }
}