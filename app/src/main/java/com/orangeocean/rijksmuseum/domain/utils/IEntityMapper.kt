package com.orangeocean.rijksmuseum.domain.utils

interface IEntityMapper<Entity, DomainModel> {
    fun toDomainModel(entity: Entity): DomainModel
    fun toEntity(domainModel: DomainModel): Entity
}