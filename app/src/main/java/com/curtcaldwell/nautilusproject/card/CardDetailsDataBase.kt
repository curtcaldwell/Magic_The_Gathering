//package com.curtcaldwell.nautilusproject.card
//
//class CardDataBase private constructor() {
//
//    var cardDao = CardDao()
//        private set
//
//    companion object {
//        @Volatile
//        private var instance: CardDataBase? = null
//
//        fun getInstance() =
//            instance
//                ?: synchronized(this) {
//                    instance
//                        ?: CardDataBase()
//                            .also { instance = it }
//                }
//    }
//}