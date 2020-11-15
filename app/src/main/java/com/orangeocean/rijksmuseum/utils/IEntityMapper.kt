package com.orangeocean.rijksmuseum.utils

interface IEntityMapper<Entity, DomainModel> {
    fun toDomainModel(entity: Entity): DomainModel
    fun toEntity(domainModel: DomainModel): Entity
}