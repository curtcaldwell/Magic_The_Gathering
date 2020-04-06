package com.curtcaldwell.nautilusproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.curtcaldwell.nautilusproject.card.CardDetailsDao

@Database(entities = [CardFromDataBase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDetailsDao(): CardDetailsDao
}
