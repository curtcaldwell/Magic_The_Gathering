package com.curtcaldwell.nautilusproject.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize

data class Card (
    @SerializedName("artist")
    val artist: String? = null,
    @SerializedName("cmc")
    val cmc: Int? = null,
    @SerializedName("colorIdentity")
    val colorIdentity: List<String>? = null,
    @SerializedName("colors")
    val colors: List<String>? = null,
    @SerializedName("flavor")
    val flavor: String? = null,
    @SerializedName("foreignNames")
    val foreignNames:@RawValue List<ForeignName>? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("layout")
    val layout: String? = null,
    @SerializedName("legalities")
    val legalities: @RawValue List<Legality>? = null,
    @SerializedName("manaCost")
    val manaCost: String? = null,
    @SerializedName("multiverseid")
    val multiverseid: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("number")
    val number: String? = null,
    @SerializedName("originalText")
    val originalText: String? = null,
    @SerializedName("originalType")
    val originalType: String? = null,
    @SerializedName("power")
    val power: String? = null,
    @SerializedName("printings")
    val printings: List<String>? = null,
    @SerializedName("rarity")
    val rarity: String? = null,
    @SerializedName("rulings")
    val rulings: @RawValue List<Ruling>? = null,
    @SerializedName("set")
    val `set`: String? = null,
    @SerializedName("setName")
    val setName: String? = null,
    @SerializedName("subtypes")
    val subtypes: List<String>? = null,
    @SerializedName("supertypes")
    val supertypes: List<String>? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("toughness")
    val toughness: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("types")
    val types: List<String>? = null,
    @SerializedName("variations")
    val variations: List<String>? = null
) : Parcelable