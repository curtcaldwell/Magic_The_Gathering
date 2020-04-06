package com.curtcaldwell.nautilusproject.card

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.curtcaldwell.nautilusproject.data.model.Card
import com.curtcaldwell.nautilusproject.database.CardFromDataBase


@Dao
interface CardDetailsDao {

    @Query("SELECT * FROM CardFromDataBase")
    fun selectPaged(): DataSource.Factory<Int, Card>

    @Insert
    fun insertAll(vararg cards: CardFromDataBase)







}