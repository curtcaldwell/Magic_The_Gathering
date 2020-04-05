package com.curtcaldwell.nautilusproject

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.curtcaldwell.nautilusproject.data.api.NetworkService
import com.curtcaldwell.nautilusproject.data.model.Card
import com.curtcaldwell.nautilusproject.data.model.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardViewModel : ViewModel() {

    private val networkService = NetworkService.getService()
    var cardList: LiveData<PagedList<Card>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val cardDataSourceFactory: CardDataSourceFactory

    init {
        cardDataSourceFactory = CardDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        cardList = LivePagedListBuilder<Int, Card>(cardDataSourceFactory, config).build()
    }




    fun getState(): LiveData<State> = Transformations.switchMap<CardDataSource,
            State>(cardDataSourceFactory.cardLiveDataSource, CardDataSource::state)

    fun retry() {
        cardDataSourceFactory.cardLiveDataSource.value?.retry()
    }


    fun listIsEmpty(): Boolean {
        return cardList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}










