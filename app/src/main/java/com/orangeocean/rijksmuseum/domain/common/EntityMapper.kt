package com.orangeocean.rijksmuseum.domain.common

interface EntityMapper<Entity, DomainModel> {
    fun toDomainModel(entity: Entity): DomainModel
    fun toEntity(domainModel: DomainModel): Entity
}