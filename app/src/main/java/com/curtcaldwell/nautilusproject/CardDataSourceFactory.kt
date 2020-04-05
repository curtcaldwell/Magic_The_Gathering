package com.curtcaldwell.nautilusproject

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.curtcaldwell.nautilusproject.data.api.NetworkService
import com.curtcaldwell.nautilusproject.data.model.Card
import io.reactivex.disposables.CompositeDisposable

class CardDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService)
    : DataSource.Factory<Int, Card>() {

    val cardLiveDataSource = MutableLiveData<CardDataSource>()

    override fun create(): DataSource<Int, Card> {
        val listDataSource = CardDataSource(networkService, compositeDisposable)
        cardLiveDataSource.postValue(listDataSource)
        return listDataSource
    }
}