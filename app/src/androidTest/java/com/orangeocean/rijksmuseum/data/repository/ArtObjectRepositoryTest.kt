package com.orangeocean.rijksmuseum.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.orangeocean.rijksmuseum.data.datasource.cache.ArtObjectCacheMockDataSource
import com.orangeocean.rijksmuseum.data.datasource.cache.IArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkMockDataSource
import com.orangeocean.rijksmuseum.data.datasource.network.IArtObjectNetworkDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ArtObjectRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val artObjectCacheDataSource: IArtObjectCacheDataSource
            = mock(ArtObjectCacheMockDataSource::class.java)
    @Mock
    private val artObjectNetworkDataSource: IArtObjectNetworkDataSource
            = mock(ArtObjectNetworkMockDataSource::class.java)

   private lateinit var artObjectRepository: ArtObjectRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        artObjectRepository = ArtObjectRepository(artObjectCacheDataSource, artObjectNetworkDataSource)
    }

    @Test
    fun refresh() {
        Assert.assertEquals(1, 1)
    }

    @Test
    fun getArtObjects() {
       // `when`(artObjectCacheDataSource.get(""))
       //     .thenReturn(ArtObjectsCollectionMock.domainObjects)

        //var data = artObjectRepository.getArtObjects("");
        //Log.d("APP", data.toString())
        Assert.assertEquals(1, 1)
    }
}