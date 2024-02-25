package com.example.homework_26.presentation.screen

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_26.databinding.FragmentMainBinding
import com.example.homework_26.presentation.adapters.CategoryItemRecyclerAdapter
import com.example.homework_26.presentation.base.BaseFragment
import com.example.homework_26.presentation.event.MainEvents
import com.example.homework_26.presentation.extension.showSnackBar
import com.example.homework_26.presentation.state.SearchStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var categoryItemAdapter: CategoryItemRecyclerAdapter

    override fun bindSetup() {
        setupRecyclerView()
    }

    override fun bindActionListeners() {
        setupSearchView()
    }

    override fun bindObservers() {
        observeSearchState()
    }

    private fun setupRecyclerView() {
        categoryItemAdapter = CategoryItemRecyclerAdapter()
        binding.rvCategory.apply {
            adapter = categoryItemAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeSearchState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchState.collect {
                    Log.d("MainFragment", "handleSearchState is called")
                    handleSearchState(it)
                }
            }
        }
    }

    private fun handleSearchState(state: SearchStates) {
        Log.d("MainFragment", "Items from API: ${state.items}")
        state.items?.let {
            categoryItemAdapter.submitList(it)
        }
        state.errorMessage?.let {
            binding.root.showSnackBar(it)
        }
        binding.progressBar.isVisible = state.isLoading
    }

    private fun setupSearchView() {
        binding.svCategory.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("MainFragment", "onQueryTextSubmit is called")
                viewModel.onEvent(MainEvents.Search(query))
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}