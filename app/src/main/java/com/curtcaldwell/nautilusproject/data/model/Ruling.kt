package com.curtcaldwell.nautilusproject.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ruling(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("text")
    val text: String? = null
) : Parcelable