package com.example.homework_26.domain.usecase

import com.example.homework_26.data.common.ErrorType
import com.example.homework_26.data.common.Resource
import com.example.homework_26.domain.model.ItemsCategory
import com.example.homework_26.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<ItemsCategory>>> {
        return mainRepository.getItemsCategory(query).map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val allItems = resource.data.flatMap { category -> flattenCategory(category) }
                    val filteredItems = allItems.filter { item -> item.name.contains(query, ignoreCase = true) }
                    Resource.Success(filteredItems)
                }
                is Resource.Error -> {
                    Resource.Error(ErrorType.FiltrationError)
                }
                is Resource.Loading -> {
                    Resource.Loading(resource.loading)
                }
            }
        }
    }

    private fun flattenCategory(category: ItemsCategory): List<ItemsCategory> {
        val items = mutableListOf<ItemsCategory>()
        items.add(category)
        category.children.forEach { child -> items.addAll(flattenCategory(child)) }
        return items
    }
}