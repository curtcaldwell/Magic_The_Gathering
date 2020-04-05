//package com.curtcaldwell.nautilusproject
//
//import com.curtcaldwell.nautilusproject.card.CardDataBase
//import com.curtcaldwell.nautilusproject.card.CardRepository
//import com.curtcaldwell.nautilusproject.card.CardViewModelFactory
//
//
//object InjectorUtils {
//    fun provideCardsViewModelFactory(): CardViewModelFactory {
//        val cardRepository = CardRepository.getInstance(CardDataBase.getInstance().cardDao)
//        return CardViewModelFactory(
//            cardRepository
//        )
//    }
//}