package com.orangeocean.rijksmuseum.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.orangeocean.rijksmuseum.BuildConfig
import com.orangeocean.rijksmuseum.data.datasource.network.ArtObjectNetworkDataSource
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.utils.IEntityMapper
import com.orangeocean.rijksmuseum.service.network.api.ArtCollectionApi
import com.orangeocean.rijksmuseum.service.network.artcollection.ArtCollectionNetworkService
import com.orangeocean.rijksmuseum.service.network.entity.ArtObjectNetworkEntity
import com.orangeocean.rijksmuseum.service.network.mappers.ArtObjectNetworkMapper
import com.orangeocean.rijksmuseum.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideArtObjectNetworkMapper(): IEntityMapper<ArtObjectNetworkEntity, ArtObject> {
        return ArtObjectNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideApiService(gson: Gson): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideArtCollectionApi(retrofit: Retrofit.Builder): ArtCollectionApi {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val httpClient = OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

            return retrofit
                .client(httpClient)
                .build()
                .create(ArtCollectionApi::class.java)
        }

        return retrofit
            .build()
            .create(ArtCollectionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideArtCollectionNetworkService(
        artCollectionApi: ArtCollectionApi
    ): ArtCollectionNetworkService {
        return ArtCollectionNetworkService(artCollectionApi)
    }

    @Singleton
    @Provides
    fun provideArtObjectNetworkDataSource(
        networkService: ArtCollectionNetworkService,
        networkMapper: ArtObjectNetworkMapper
    ): ArtObjectNetworkDataSource {
        return ArtObjectNetworkDataSource(networkService, networkMapper)
    }
}
