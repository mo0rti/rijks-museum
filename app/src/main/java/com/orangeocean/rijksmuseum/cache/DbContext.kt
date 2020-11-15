package com.orangeocean.rijksmuseum.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orangeocean.rijksmuseum.cache.entity.ArtObjectCacheEntity

@Database(entities = [ArtObjectCacheEntity::class], version = 1)
abstract class DbContext: RoomDatabase() {

}