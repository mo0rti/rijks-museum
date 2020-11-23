package com.orangeocean.rijksmuseum.mock

import com.orangeocean.rijksmuseum.domain.model.ArtObject

object ArtObjectsCollectionMockData {

    private val artObjectDomain1 = ArtObject("1", "title 1", "thumbnail 1",
        "image url 1", "artist name 1", "description 1")

    private val artObjectDomain2 = ArtObject("2", "title 2", "thumbnail 2",
        "image url 2", "artist name 2", "description 2")

    val domainObjects = listOf(artObjectDomain1, artObjectDomain2)
}