package com.orangeocean.rijksmuseum.mock

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity
import com.orangeocean.rijksmuseum.service.network.entity.ArtCollectionNetworkEntity
import com.orangeocean.rijksmuseum.service.network.entity.ArtObjectNetworkEntity
import com.orangeocean.rijksmuseum.service.network.entity.WebImageNetworkEntity

object ArtObjectsCollectionMock {

    private val artObjectDomain1 = ArtObject("1", "title 1", "thumbnail 1",
        "image url 1", "artist name 1", "description 1")

    private val artObjectDomain2 = ArtObject("2", "title 2", "thumbnail 2",
        "image url 2", "artist name 2", "description 2")

    val domainObjects = listOf(artObjectDomain1, artObjectDomain2)

    private val artObjectCache1 = ArtObjectCacheEntity("1", "title 1", "thumbnail 1",
        "image url 1", "artist name 1", "description 1")

    private val artObjectCache2 = ArtObjectCacheEntity("2", "title 2", "thumbnail 2",
        "image url 2", "artist name 2", "description 2")

    val cacheObjects = listOf(artObjectCache1, artObjectCache2)

    private val artObjectNetworkEntity1 = ArtObjectNetworkEntity(
        "1",
        "title 1",
        "first maker 1",
        WebImageNetworkEntity("guid 1", "image url 1"),
        WebImageNetworkEntity("guid 2", "image url 2"),
    "long title 1")

    val networkObjects = ArtCollectionNetworkEntity(2, listOf(artObjectNetworkEntity1))
}