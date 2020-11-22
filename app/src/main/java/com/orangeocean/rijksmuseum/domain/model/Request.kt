package com.orangeocean.rijksmuseum.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Request(
    var id: Long = 0,
    val url: String,
    var isSynced: Boolean = false,
) : Parcelable {
    constructor(url: String): this(0, url, false)
}