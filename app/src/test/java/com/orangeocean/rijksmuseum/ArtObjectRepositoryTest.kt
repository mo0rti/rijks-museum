package com.orangeocean.rijksmuseum

import com.nhaarman.mockitokotlin2.*
import com.orangeocean.rijksmuseum.data.datasource.cache.artobject.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.cache.artobject.ArtObjectCacheDataSourceMock
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSourceMock
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepositoryImpl
import com.orangeocean.rijksmuseum.domain.common.DataState
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.mock.ArtObjectsCollectionMockData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ArtObjectRepositoryTest {

    @Mock
    private val artObjectCacheDataSource: ArtObjectCacheDataSource =
        mock(ArtObjectCacheDataSourceMock::class.java)

    @Mock
    private val artObjectNetworkDataSource: ArtObjectNetworkDataSource =
        mock(ArtObjectNetworkDataSourceMock::class.java)

    private lateinit var artObjectRepository: ArtObjectRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        artObjectRepository =
            ArtObjectRepositoryImpl(artObjectCacheDataSource, artObjectNetworkDataSource)
    }

    @Test
    fun `Should emits list of Art Objects when it called successfully`() = runBlockingTest {
        val loadingState = DataState.Loading
        val successState = DataState.Success(ArtObjectsCollectionMockData.domainObjects)

        `when`(artObjectCacheDataSource.get(any()))
            .thenReturn(ArtObjectsCollectionMockData.domainObjects)

        val flow = artObjectRepository.getArtObjects("")
        val flowStates = mutableListOf<DataState<List<ArtObject>>>()
        flow.collect { it: DataState<List<ArtObject>> ->
            flowStates.add(it)
        }

        Assert.assertEquals(loadingState, flowStates[0])
        Assert.assertEquals(successState, flowStates[1])
    }

    @Test
    fun `Should emits error when an exception happen`() = runBlockingTest {
        val loadingState = DataState.Loading

        `when`(artObjectCacheDataSource.get(any()))
            .doAnswer {
                throw Exception()
            }

        var flow = artObjectRepository.getArtObjects("")
        val flowStates = mutableListOf<DataState<List<ArtObject>>>()
        flow.collect { it: DataState<List<ArtObject>> ->
            flowStates.add(it)
        }

        Assert.assertEquals(loadingState, flowStates[0])
        Assert.assertTrue(flowStates[1] is DataState.Error)
    }

    @Test
    fun `Should call the network DS first and then cache DS when refresh the data`() = runBlockingTest {

        `when`(artObjectCacheDataSource.get(any()))
            .thenReturn(ArtObjectsCollectionMockData.domainObjects)

        `when`(artObjectNetworkDataSource.get(any()))
            .thenReturn(ArtObjectsCollectionMockData.domainObjects)

        artObjectRepository
            .refresh("")
            .collect()

        var invokeOrder = inOrder(artObjectNetworkDataSource, artObjectCacheDataSource)
        invokeOrder.verify(artObjectNetworkDataSource, times(1)).get("")
        invokeOrder.verify(artObjectCacheDataSource, times(1)).get("");
    }

    @Test
    fun `Should not call the network DS when cache data is available`() = runBlockingTest {

        `when`(artObjectCacheDataSource.get(any()))
            .thenReturn(ArtObjectsCollectionMockData.domainObjects)

        `when`(artObjectNetworkDataSource.get(any()))
            .thenReturn(ArtObjectsCollectionMockData.domainObjects)

        artObjectRepository
            .getArtObjects("")
            .collect()

        verify(artObjectCacheDataSource, times(1)).get("");
        verify(artObjectNetworkDataSource, times(0)).get("")
    }

    @Test
    fun `Should call the network DS when cache data is empty`() = runBlockingTest {

        `when`(artObjectCacheDataSource.get(any()))
            .thenReturn(listOf())

        `when`(artObjectNetworkDataSource.get(any()))
            .thenReturn(ArtObjectsCollectionMockData.domainObjects)

        artObjectRepository
            .getArtObjects("")
            .collect()

        verify(artObjectNetworkDataSource, times(1)).get("")
    }
}