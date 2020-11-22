package com.orangeocean.rijksmuseum

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.orangeocean.rijksmuseum.data.datasource.cache.ArtObjectCacheMockDataSource
import com.orangeocean.rijksmuseum.data.datasource.cache.IArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkMockDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.IArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.state.DataState
import com.orangeocean.rijksmuseum.mock.ArtObjectsCollectionMockData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.io.IOException

@ExperimentalCoroutinesApi
class ArtObjectRepositoryTest {

    @Mock
    private val artObjectCacheDataSource: IArtObjectCacheDataSource =
        mock(ArtObjectCacheMockDataSource::class.java)

    @Mock
    private val artObjectNetworkDataSource: IArtObjectNetworkDataSource =
        mock(ArtObjectNetworkMockDataSource::class.java)

    private lateinit var artObjectRepository: ArtObjectRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        artObjectRepository =
            ArtObjectRepository(artObjectCacheDataSource, artObjectNetworkDataSource)
    }

    @Test
    fun `Should emits list of Art Objects when it called successfully`() = runBlockingTest {
        val loadingState = DataState.Loading
        val successState = DataState.Success(ArtObjectsCollectionMockData.domainObjects)

        artObjectCacheDataSource.stub {
            onBlocking {
                get("")
            } doReturn ArtObjectsCollectionMockData.domainObjects
        }
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
        val errorState = DataState.Error(Exception("An error has been occurred during fetching data"))

        artObjectCacheDataSource.stub {
            onBlocking {
                get("")
            } doAnswer {
                throw IOException()
            }
        }

        var flow = artObjectRepository.getArtObjects("")
        val flowStates = mutableListOf<DataState<List<ArtObject>>>()
        flow.collect { it: DataState<List<ArtObject>> ->
            flowStates.add(it)
        }

        Assert.assertEquals(loadingState, flowStates[0])
        //Assert.assertEquals(errorState, flowStates[1])
    }
}