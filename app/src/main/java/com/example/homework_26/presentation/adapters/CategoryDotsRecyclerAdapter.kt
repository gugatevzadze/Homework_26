package com.example.homework_26.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_26.databinding.ItemDotsBinding

class CategoryDotsRecyclerAdapter(
    private val item: Int
) : RecyclerView.Adapter<CategoryDotsRecyclerAdapter.DotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val binding = ItemDotsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {}

    override fun getItemCount(): Int = minOf(item, 4)

    inner class DotViewHolder(binding: ItemDotsBinding) : RecyclerView.ViewHolder(binding.root)
}