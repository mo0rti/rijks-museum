package com.orangeocean.rijksmuseum.service.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class RequestCacheEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "is_synced", defaultValue = "false")
    var isSynced: Boolean,
)