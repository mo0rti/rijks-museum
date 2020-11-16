package com.orangeocean.rijksmuseum.ui.artcollection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.utils.DataState
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
        initRecyclerView()
        subscribeObservers()
        artCollectionViewModel.setStateEvent(StateEvent.GetArtObjectEvents)
    }

    private fun subscribeObservers() {
        artCollectionViewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<ArtObject>> -> {
                    displayLoading(false)
                    setRecyclerViewData(dataState.data)
                }

                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayLoading(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    private fun displayLoading(isVisible: Boolean) {
        // TODO: add progress bar
    }

    private fun setRecyclerViewData(items: List<ArtObject>) {
        artCollectionRecyclerAdapter.setListItem(items)
        artCollectionRecyclerAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        recycler_view_art_collection.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(ArtCollectionRecyclerItemDecoration(resources.getInteger(R.integer.list_item_card_padding)))
            artCollectionRecyclerAdapter = ArtCollectionRecyclerAdapter()
            adapter = artCollectionRecyclerAdapter
        }
    }
}