package com.example.androidapptemplate.features.webapi.unsplash.gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.util.handleNetworkConnectionError
import com.example.androidapptemplate.core.util.handleUnSplashUnAuthorizedError
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.FragmentImageSearchGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class ImageSearchGalleryFragment : Fragment(R.layout.fragment_image_search_gallery) {
    private val viewModel by viewModels<ImageSearchGalleryViewModel>()
    private val binding by viewBindings(FragmentImageSearchGalleryBinding::bind)
    private val adapter = UnsplashPhotoAdapter {
        val action = ImageSearchGalleryFragmentDirections.goToDetailsDest(it)
        findNavController().navigate(action)
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.addLoadStateListener {
            if (it.source.refresh is LoadState.NotLoading && it.source.append.endOfPaginationReached && adapter.itemCount < 1) {
                Toast.makeText(requireContext(), "empty list.", Toast.LENGTH_SHORT).show()
            }
            // paging3: error handling
            val errorState = when {
                it.source.prepend is LoadState.Error -> {
                    it.source.prepend as LoadState.Error
                }
                it.source.append is LoadState.Error -> {
                    it.source.append as LoadState.Error
                }
                it.source.refresh is LoadState.Error -> {
                    it.source.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.let { it ->
                handleUnSplashUnAuthorizedError(throwable = it.error, onUnAuthorizedAction = {})
                handleNetworkConnectionError(throwable = it.error, onRetry = { adapter.refresh() })
            }
        }

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter { adapter.retry() },
                footer = UnsplashPhotoLoadStateAdapter { adapter.retry() },
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photos.collectLatest {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.recyclerView.scrollToPosition(0)
                }
        }

        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery, menu)
        val clearItem = menu.findItem(R.id.action_clear)
        clearItem.setOnMenuItemClickListener {
            viewModel.clearList()
            true
        }
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query ?: return true
                binding.recyclerView.scrollToPosition(0)
                viewModel.searchPhotos(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}