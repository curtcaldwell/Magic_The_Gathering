package com.curtcaldwell.nautilusproject.card


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.curtcaldwell.nautilusproject.data.model.Card

class CardDetailsViewModel(private val card : Card?) : ViewModel() {


    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    init{
        _name.value = card?.name
    }

    private val _cardImage = MutableLiveData<String>()
    val cardImage : LiveData<String>
    get() = _cardImage
    init {
        _cardImage.value = card?.imageUrl
    }

    private val _cardText = MutableLiveData<String>()
    val cardText : LiveData<String>
        get() = _cardText
    init {
        _cardText.value = card?.originalText
    }

    private val _cardRarity = MutableLiveData<String>()
    val cardRarity : LiveData<String>
        get() = _cardRarity
    init {
        _cardRarity.value = card?.rarity
    }

    private val _cardType = MutableLiveData<String>()
    val cardType : LiveData<String>
        get() = _cardType
    init {
        _cardType.value = card?.type
    }
    private val _cardSetName = MutableLiveData<String>()
    val cardSetName : LiveData<String>
        get() = _cardSetName
    init {
        _cardSetName.value = card?.setName
    }
    private val _cardArtist = MutableLiveData<String>()
    val cardArtist : LiveData<String>
        get() = _cardArtist
    init {
        _cardArtist.value = card?.artist
    }
    private val _cardFlavor = MutableLiveData<String>()
    val cardFlavor : LiveData<String>
        get() = _cardFlavor
    init {
        _cardFlavor.value = card?.flavor
    }








}