package com.example.homework_26.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_26.databinding.ItemCategoryBinding
import com.example.homework_26.presentation.model.ItemsCategoryModel

class CategoryItemRecyclerAdapter : ListAdapter<ItemsCategoryModel, CategoryItemRecyclerAdapter.CategoryItemViewHolder>(
    CategoryItemDiffUtil()
) {

    private lateinit var categoryDotsAdapter: CategoryDotsRecyclerAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemRecyclerAdapter.CategoryItemViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryItemRecyclerAdapter.CategoryItemViewHolder,
        position: Int
    ) {
        holder.bind()
    }

    inner class CategoryItemViewHolder(
        private val binding: ItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: ItemsCategoryModel
        fun bind() {
            item = currentList[adapterPosition]
            binding.apply {
                tvCategory.text = item.name
            }
            categoryDotsAdapter = CategoryDotsRecyclerAdapter(
                item.parentCount
            )
            binding.rvDots.apply {
                adapter = categoryDotsAdapter
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private class CategoryItemDiffUtil : DiffUtil.ItemCallback<ItemsCategoryModel>() {
        override fun areItemsTheSame(
            oldItem: ItemsCategoryModel,
            newItem: ItemsCategoryModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ItemsCategoryModel,
            newItem: ItemsCategoryModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}