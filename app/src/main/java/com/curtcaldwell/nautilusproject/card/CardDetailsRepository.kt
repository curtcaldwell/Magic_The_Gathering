//package com.curtcaldwell.nautilusproject.card
//
//import com.curtcaldwell.nautilusproject.data.model.Card
//
//class CardRepository private constructor(private val cardDao: CardDao) {
//
//    fun addCard(card: Card) {
//        cardDao.addCard(card)
//    }
//
//    fun getCards() = cardDao.getCards()
//
//    companion object {
//        @Volatile
//        private var instance: CardRepository? = null
//
//        fun getInstance(cardDao: CardDao) : CardRepository {
//            return instance
//                ?: synchronized(this) {
//                    instance
//                        ?: CardRepository(cardDao)
//                            .also { instance = it }
//                }
//        }
//    }
//}
//
//
