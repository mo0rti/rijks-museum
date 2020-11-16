package com.orangeocean.rijksmuseum.ui.artcollection

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.utils.DataState
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
    val dataState: LiveData<DataState<List<ArtObject>>>
        get() = _dataState

    fun setStateEvent(artCollectionStateEvent: ArtCollectionStateEvent) {
        viewModelScope.launch {
            when (artCollectionStateEvent) {
                is ArtCollectionStateEvent.GetArtObjectEvents -> {
                    artObjectRepository
                        .getArtObjects()
                        .onEach { dataState -> _dataState.value = dataState }
                        .launchIn(viewModelScope)
                }
                is ArtCollectionStateEvent.SearchArtObjectEvents -> {
                    // TODO: search
                }
                else -> {
                    // TODO: nothing happening
                }
            }
        }
    }
}
