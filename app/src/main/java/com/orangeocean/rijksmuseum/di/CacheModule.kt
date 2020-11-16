package com.orangeocean.rijksmuseum.di

import android.content.Context
import androidx.room.Room
import com.orangeocean.rijksmuseum.cache.DbContext
import com.orangeocean.rijksmuseum.cache.dao.ArtObjectDao
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
    fun provideDbContext(@ApplicationContext context: Context): DbContext {
        return Room
            .databaseBuilder(
                context,
                DbContext::class.java,
                DbContext.DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArtObjectDao(db: DbContext): ArtObjectDao {
        return db.artObjectDao();
    }
}