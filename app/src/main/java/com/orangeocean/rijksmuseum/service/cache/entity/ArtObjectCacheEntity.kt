package com.orangeocean.rijksmuseum.service.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artObjects")
data class ArtObjectCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    @ColumnInfo(name= "artistName")
    var artistName: String,
)