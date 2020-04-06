package com.curtcaldwell.nautilusproject

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.curtcaldwell.nautilusproject.data.api.NetworkService
import com.curtcaldwell.nautilusproject.data.model.Card
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class CardDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService)
    : DataSource.Factory<Int, Card>() {

    val cardLiveDataSource = MutableLiveData<CardDataSource>()
    private val searchSubject : PublishSubject<String> = PublishSubject.create()
    private var stringSearch = ""

    override fun create(): DataSource<Int, Card> {
        val listDataSource = CardDataSource(networkService, compositeDisposable, stringSearch)
        cardLiveDataSource.postValue(listDataSource)
        return listDataSource
    }
    fun updateSearch(search: String){
        searchSubject.onNext(search)
    }
    init{
        searchSubject.debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged().subscribe{
                stringSearch = it
                cardLiveDataSource.value?.invalidate()
            }

    }
}