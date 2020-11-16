package com.orangeocean.rijksmuseum.ui.artcollection

sealed class ArtCollectionStateEvent {
    object GetArtObjectEvents : ArtCollectionStateEvent()

    object SearchArtObjectEvents : ArtCollectionStateEvent()
}