package com.orangeocean.rijksmuseum.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Request(
    var id: Long,
    var url: String,
    var syncPending: Boolean,
) : Parcelable