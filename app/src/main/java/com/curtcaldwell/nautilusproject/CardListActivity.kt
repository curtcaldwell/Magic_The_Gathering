package com.curtcaldwell.nautilusproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curtcaldwell.nautilusproject.card.CardDetailsActivity
import com.curtcaldwell.nautilusproject.data.api.NetworkService
import com.curtcaldwell.nautilusproject.data.model.Card
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.txt_error

//interface ViewModelProviderFactory<T, VM> {
//    val modelClass: Class<T>
//    val viewModelClass: Class<VM>
//    fun getId(model: T): String
//    interface ProviderFactory<T>: ViewModelProvider.Factory {
//    }
//    fun create(): ProviderFactory<T>
//}


interface CardClickListener {


    fun onCardClick(card: Card, imageView: ImageView)
}

class CardListActivity : AppCompatActivity() {

    private lateinit var viewModel: CardViewModel
    private lateinit var cardListAdapter: CardListPagingAdapter
    private val editText : EditText by lazy { search_edit_text }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this, CardViewModelFactory(applicationContext))
            .get(CardViewModel::class.java)

        initAdapter()
        initState()
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.cardDataSourceFactory.updateSearch(p0.toString())
            }

        })
    }

//    fun create(): ViewModelProviderFactory.ProviderFactory<Any> {
//        return object : ViewModelProviderFactory.ProviderFactory<Any> {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return CardViewModel(
//                    NetworkService.getService(applicationContext)
//                ) as T
//            }
//
//            override val model: Any
//                get() = TODO("Not yet implemented")
//        }
//    }



    private fun initAdapter() {

        cardListAdapter = CardListPagingAdapter(object : CardClickListener {
            override fun onCardClick(card: Card, imageView: ImageView) {
                imageView.transitionName = card.name
                val intent = Intent(this@CardListActivity, CardDetailsActivity::class.java)
                intent.putExtra("card_extra", card)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@CardListActivity,
                    imageView as View,
                    card.name?:""
                )
                this@CardListActivity.startActivity(intent, options.toBundle())

            }

        }) { viewModel.retry() }
        rv_main.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        rv_main.adapter = cardListAdapter
        viewModel.cardList.observe(this, Observer {
            cardListAdapter.submitList(it)
        })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                cardListAdapter.setState(state ?: State.DONE)
            }
        })
    }


}


class CardViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CardViewModel(NetworkService.getService(context)) as T
    }

}
