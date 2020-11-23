package com.orangeocean.rijksmuseum.ui.artcollection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.domain.common.DataState
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.utils.AppLogger
import com.orangeocean.rijksmuseum.utils.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.TimeUnit

class ArtCollectionViewModel
@ViewModelInject
constructor(
    private val artObjectRepository: ArtObjectRepository
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
        startRefreshJob()
        setStateEvent(ArtCollectionStateEvent.GetArtObjectEvents)
    }

    override fun onCleared() {
        super.onCleared()
        refreshCoroutineScope
            .coroutineContext
            .cancelChildren()
    }

    private fun startRefreshJob() {
        CoroutineScope(Dispatchers.IO + refreshJob).launch {
            while (true) {
                delay(TimeUnit.MINUTES.toMillis(Constants.CACHE_REFRESH_INTERVAL_MIN))
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
