package com.orangeocean.rijksmuseum.network.mappers

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.network.entity.ArtObjectNetworkEntity
import com.orangeocean.rijksmuseum.network.entity.WebImageNetworkEntity
import com.orangeocean.rijksmuseum.utils.IEntityMapper
import java.util.*
import javax.inject.Inject

class ArtObjectNetworkMapper
@Inject
constructor(): IEntityMapper<ArtObjectNetworkEntity, ArtObject> {
    override fun toDomainModel(entity: ArtObjectNetworkEntity): ArtObject {
        return ArtObject(
            id = entity.id,
            title = entity.title,
            thumbnail = entity.headerImage.url,
            imageUrl = entity.webImage.url,
        )
    }

    override fun toEntity(domainModel: ArtObject): ArtObjectNetworkEntity {
        return ArtObjectNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            headerImage = WebImageNetworkEntity(
                guid = UUID.randomUUID().toString(),
                url = domainModel.thumbnail,
            ),
            webImage = WebImageNetworkEntity(
                guid = UUID.randomUUID().toString(),
                url = domainModel.imageUrl,
            )
        )
    }

    fun toDomainModelList(entities: List<ArtObjectNetworkEntity>): List<ArtObject> {
        return entities.map { toDomainModel(it) }
    }
}