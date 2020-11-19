package com.orangeocean.rijksmuseum.ui.artcollection

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.state.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ArtCollectionViewModel
@ViewModelInject
constructor(
    private val artObjectRepository: ArtObjectRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<ArtObject>>> = MutableLiveData()
    private var _artistName: String = "Rembrandt van Rijn"

    val dataState: LiveData<DataState<List<ArtObject>>>
        get() = _dataState

    fun setArtistName(artistName: String) {
        _artistName = artistName
    }
    fun setStateEvent(artCollectionStateEvent: ArtCollectionStateEvent) {
        viewModelScope.launch {
            when (artCollectionStateEvent) {
                is ArtCollectionStateEvent.GetArtObjectEvents -> {
                    artObjectRepository
                        .getArtObjects(_artistName)
                        .onEach { dataState -> _dataState.value = dataState }
                        .launchIn(viewModelScope)
                }
                else -> null
            }
        }
    }

    /*
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                artObjectRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(playlist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }*/
}
