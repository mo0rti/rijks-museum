package com.orangeocean.rijksmuseum.service.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orangeocean.rijksmuseum.service.cache.entity.RequestCacheEntity

@Dao
interface RequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(requestCacheEntity: RequestCacheEntity): Long

    @Query("UPDATE requests SET sync_pending=1 WHERE id=:id")
    suspend fun updateToSynced(id: Long)

    @Query("SELECT * FROM requests WHERE sync_pending=0")
    suspend fun getUnSynced(): List<RequestCacheEntity>

    @Query("SELECT * from requests where id = :id LIMIT 1")
    suspend fun getById(id: Long): RequestCacheEntity
}