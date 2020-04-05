package com.curtcaldwell.nautilusproject.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ForeignName(
    @SerializedName("flavor")
    val flavor: @RawValue Any? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("multiverseid")
    val multiverseid: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("text")
    val text: String? = null
) : Parcelable