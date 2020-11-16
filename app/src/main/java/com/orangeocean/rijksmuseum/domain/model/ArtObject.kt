package com.orangeocean.rijksmuseum.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtObject(
    var id: String,
    var title: String,
    var thumbnail: String,
    var imageUrl: String,
) : Parcelable {

}