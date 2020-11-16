package com.orangeocean.rijksmuseum.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.orangeocean.rijksmuseum.network.ArtCollectionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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
    fun provideApiService(gson: Gson): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl("https://www.rijksmuseum.nl/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideArtCollectionApi(retrofit: Retrofit.Builder): ArtCollectionApi {
        return retrofit
            .build()
            .create(ArtCollectionApi::class.java)
    }
}
