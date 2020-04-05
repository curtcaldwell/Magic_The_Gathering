package com.curtcaldwell.nautilusproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardFromDataBase (

    @PrimaryKey(autoGenerate = true) val uid: Int?,

    @ColumnInfo val title: String



)
