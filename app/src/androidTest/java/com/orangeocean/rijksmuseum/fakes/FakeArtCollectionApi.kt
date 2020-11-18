package com.orangeocean.rijksmuseum.fakes

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orangeocean.rijksmuseum.service.network.api.ArtCollectionApi
import com.orangeocean.rijksmuseum.service.network.entity.ArtCollectionNetworkEntity
import com.orangeocean.rijksmuseum.common.JsonUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeArtCollectionApi
@Inject
constructor(
    private val jsonUtils: JsonUtils
): ArtCollectionApi {
    override suspend fun get(apiKey: String, artistName: String): ArtCollectionNetworkEntity {
        val rawData = jsonUtils.readJsonFromAssets("art-objects.json")
        return Gson().fromJson(
            rawData,
            object : TypeToken<ArtCollectionNetworkEntity> (){}.type
        )
    }
}