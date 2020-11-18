package com.orangeocean.rijksmuseum.service.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity

@Dao
interface ArtObjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artObjectCacheEntity: ArtObjectCacheEntity): Long

    @Query("SELECT * FROM artObjects WHERE artistName LIKE :artistName")
    suspend fun get(artistName: String): List<ArtObjectCacheEntity>
}