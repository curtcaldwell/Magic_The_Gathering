package com.curtcaldwell.nautilusproject.card


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.curtcaldwell.nautilusproject.data.model.Card

class CardDetailsViewModel(private val card : Card?) : ViewModel() {

    private val liveCard = MutableLiveData<Card>()

    val name : LiveData<String> = Transformations.map(liveCard) { card ->
        card.name
    }

    val cardDescription : LiveData<String> = Transformations.map(liveCard){card ->
        card.text
    }

    val cardRarity : LiveData<String> = Transformations.map(liveCard){card ->
        card.rarity
    }
    val cardType : LiveData<String> = Transformations.map(liveCard){card ->
        card.type
    }
    val cardFlavor : LiveData<String> = Transformations.map(liveCard){card ->
        card.flavor
    }
    val cardSetName : LiveData<String> = Transformations.map(liveCard){card ->
        card.setName
    }
    val cardArtist : LiveData<String> = Transformations.map(liveCard){card ->
        card.artist
    }
    init{
        liveCard.value = card
    }
}