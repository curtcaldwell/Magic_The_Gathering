//package com.curtcaldwell.nautilusproject.card
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
//@Suppress("UNCHECKED_CAST")
//class CardViewModelFactory(private val cardRepository: CardRepository) :
//    ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return CardViewModel(cardRepository) as T
//    }
//
//}