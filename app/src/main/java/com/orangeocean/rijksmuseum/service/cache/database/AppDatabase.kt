package com.orangeocean.rijksmuseum.service.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity
import com.orangeocean.rijksmuseum.service.cache.entity.RequestCacheEntity

@Database(entities = [ArtObjectCacheEntity::class, RequestCacheEntity::class], version = 6)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME: String = "museum_db"
    }

    abstract fun artObjectDao():  ArtObjectDao

    abstract fun requestDao():  RequestDao
}