package com.example.homework_26.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_26.databinding.ItemDotsBinding
import com.example.homework_26.presentation.model.ItemsCategoryModel

class CategoryDotsRecyclerAdapter(
    private val item: ItemsCategoryModel
) : RecyclerView.Adapter<CategoryDotsRecyclerAdapter.DotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val binding = ItemDotsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        holder.bind(item)
    }

    override fun getItemCount(): Int = 1

    inner class DotViewHolder(private val binding: ItemDotsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dots = mutableListOf<AppCompatImageView>()

        fun bind(item: ItemsCategoryModel) {
            val parentCount = item.parentCount
            dots.clear()
            for (i in 0 until parentCount) {
                val dot = AppCompatImageView(binding.root.context).apply {
                    visibility = View.VISIBLE
                }
                dots.add(dot)
                binding.llDots.addView(dot)
            }
        }
    }
}