package com.orangeocean.rijksmuseum.ui.artcollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.common.DataState
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_art_collection.*

@AndroidEntryPoint
class ArtCollectionFragment : Fragment() {

    private val artCollectionViewModel: ArtCollectionViewModel by viewModels()
    private lateinit var artCollectionRecyclerAdapter: ArtCollectionRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_art_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initSearchView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        artCollectionViewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<ArtObject>> ->
                {
                    normalState(dataState.data.isNotEmpty())
                    setRecyclerViewData(dataState.data)
                }

                is DataState.Error -> {
                    errorState()
                    displayError(dataState.exception.message, dataState.exception)
                }

                is DataState.Loading -> {
                    loadingState()
                }
            }
        })
    }

    private fun normalState(hasData: Boolean) {
        recycler_view_art_collection.visibility = if (hasData) View.VISIBLE else View.GONE
        empty_list_view.visibility = if (!hasData) View.VISIBLE else View.GONE
        progressBarLoading.visibility = View.GONE
    }

    private fun displayError(message: String?, exception: Exception?) {
        Toast.makeText(activity, "An Error occurred $message", Toast.LENGTH_LONG).show();
    }

    private fun errorState() {
        progressBarLoading.visibility = View.GONE
    }

    private fun loadingState() {
        progressBarLoading.visibility = View.VISIBLE
        empty_list_view.visibility = View.GONE
        recycler_view_art_collection.visibility = View.GONE
    }

    private fun setRecyclerViewData(items: List<ArtObject>) {
        artCollectionRecyclerAdapter.setListItem(items)
        artCollectionRecyclerAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        recycler_view_art_collection.apply {
            layoutManager = LinearLayoutManager(activity)
            artCollectionRecyclerAdapter = ArtCollectionRecyclerAdapter()
            adapter = artCollectionRecyclerAdapter
        }
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isEmpty()!!)
                    return false;
                artCollectionViewModel.setArtistName(query)
                search_view.setQuery("", false)
                search_view.clearFocus()
                return true;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}