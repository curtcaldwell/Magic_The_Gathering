package com.curtcaldwell.nautilusproject.data.model


import android.os.Parcelable
import com.curtcaldwell.nautilusproject.data.model.Card
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result (
    @SerializedName("cards")
    val cards: List<Card>? = null
) : Parcelable