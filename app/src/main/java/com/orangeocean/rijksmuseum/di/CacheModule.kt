package com.orangeocean.rijksmuseum.di

import android.content.Context
import androidx.room.Room
import com.orangeocean.rijksmuseum.data.datasource.cache.ArtObjectCacheDataSource
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.utils.IEntityMapper
import com.orangeocean.rijksmuseum.service.cache.artobject.ArtObjectCacheService
import com.orangeocean.rijksmuseum.service.cache.database.AppDatabase
import com.orangeocean.rijksmuseum.service.cache.database.ArtObjectDao
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity
import com.orangeocean.rijksmuseum.service.cache.mappers.ArtObjectCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): IEntityMapper<ArtObjectCacheEntity, ArtObject> {
        return ArtObjectCacheMapper()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArtObjectDao(db: AppDatabase): ArtObjectDao {
        return db.artObjectDao();
    }

    @Singleton
    @Provides
    fun provideArtObjectCacheService(
        artObjectDao: ArtObjectDao
    ): ArtObjectCacheService {
        return ArtObjectCacheService(artObjectDao)
    }

    @Singleton
    @Provides
    fun provideArtObjectCacheDataSource(
        cacheService: ArtObjectCacheService,
        cacheMapper: ArtObjectCacheMapper
    ): ArtObjectCacheDataSource {
        return ArtObjectCacheDataSource(cacheService, cacheMapper)
    }
}