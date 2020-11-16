package com.orangeocean.rijksmuseum.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orangeocean.rijksmuseum.cache.dao.ArtObjectDao
import com.orangeocean.rijksmuseum.cache.entity.ArtObjectCacheEntity

@Database(entities = [ArtObjectCacheEntity::class], version = 2)
abstract class DbContext: RoomDatabase() {

    companion object {
        val DB_NAME: String = "museum_db"
    }

    abstract fun artObjectDao():  ArtObjectDao
}