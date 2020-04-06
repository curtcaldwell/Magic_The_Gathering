package com.curtcaldwell.nautilusproject

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.curtcaldwell.nautilusproject.data.api.NetworkService
import com.curtcaldwell.nautilusproject.data.model.Card
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CardDataSource(
    private val networkService: NetworkService,
    private val compositeDisposable: CompositeDisposable,
    private val stringSearch : String)
    : PageKeyedDataSource<Int, Card>() {


    var state: MutableLiveData<State> = MutableLiveData()
    private  var retryCompletable: Completable? = null



    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Card>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getResult(stringSearch, 1, params.requestedLoadSize)
                .map { it.cards?.filter { !it.imageUrl.isNullOrEmpty() } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        cards ->
                        cards?.let {
                            updateState(State.DONE)
                            callback.onResult(cards, null, 2)
                        }
                    },{
                        updateState(State.ERROR)
                        setRetry(Action {loadInitial(params, callback)})
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Card>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getResult(stringSearch, params.key, params.requestedLoadSize)
                .map { it.cards?.filter { !it.imageUrl.isNullOrEmpty() } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { cards ->
                        cards?.let {
                            updateState(State.DONE)
                            callback.onResult(cards, params.key + 1)
                        }


                },{
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Card>) {
    }
    private fun updateState(state: State) {
        this.state.postValue(state)
    }
    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}

//
//        api.getResult("imageUrl", page)
//            .map { it.cards.filter { !it.imageUrl.isNullOrEmpty() } }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ cards -> listOberservable.value = cards
//                // populate viewmodel observable list
////
////                Log.d("response", cardList.toString())
//
//
//            }, { e ->
//                e.printStackTrace()
//            }
//
//            )