package com.orangeocean.rijksmuseum.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orangeocean.rijksmuseum.cache.entity.ArtObjectCacheEntity

@Dao
interface ArtObjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artObjectCacheEntity: ArtObjectCacheEntity): Long

    @Query("SELECT * FROM artObjects")
    suspend fun get(): List<ArtObjectCacheEntity>
}