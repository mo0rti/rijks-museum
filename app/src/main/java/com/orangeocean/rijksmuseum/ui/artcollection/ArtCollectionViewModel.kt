package com.orangeocean.rijksmuseum.ui.artcollection

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.state.DataState
import com.orangeocean.rijksmuseum.utils.AppLogger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ArtCollectionViewModel
@ViewModelInject
constructor(
    private val artObjectRepository: ArtObjectRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<ArtObject>>> = MutableLiveData()
    private var _artistName = "Rembrandt van Rijn"
    private var refreshJob: Job = Job()
    private var refreshCoroutineScope  = CoroutineScope(Dispatchers.IO + refreshJob)

    val dataState: LiveData<DataState<List<ArtObject>>>
        get() = _dataState

    fun setArtistName(artistName: String) {
        _artistName = artistName
        setStateEvent(ArtCollectionStateEvent.GetArtObjectEvents)
    }

    init {
        CoroutineScope(Dispatchers.IO + refreshJob).launch {
            while (true) {
                //delay(TimeUnit.MINUTES.toMillis(Constants.CACHE_REFRESH_INTERVAL_MIN))
                delay(6000)
                AppLogger.logInfo("Data Refreshing for $_artistName")
                if (!_artistName.isNullOrBlank()) {
                    artObjectRepository
                        .refresh(_artistName)
                        .onEach { dataState -> _dataState.value = dataState }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        refreshCoroutineScope
            .coroutineContext
            .cancelChildren()
    }

    private fun setStateEvent(artCollectionStateEvent: ArtCollectionStateEvent) {
        viewModelScope.launch {
            when (artCollectionStateEvent) {
                is ArtCollectionStateEvent.GetArtObjectEvents -> {
                    artObjectRepository
                        .getArtObjects(_artistName)
                        .onEach { dataState -> _dataState.value = dataState }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}
