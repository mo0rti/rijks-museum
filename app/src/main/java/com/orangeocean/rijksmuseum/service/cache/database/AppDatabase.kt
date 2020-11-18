package com.orangeocean.rijksmuseum.service.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity

@Database(entities = [ArtObjectCacheEntity::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME: String = "museum_db"
    }

    abstract fun artObjectDao():  ArtObjectDao
}