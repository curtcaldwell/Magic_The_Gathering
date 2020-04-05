package com.curtcaldwell.nautilusproject.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Legality(
    @SerializedName("format")
    val format: String? = null,
    @SerializedName("legality")
    val legality: String? = null
) : Parcelable