package com.curtcaldwell.nautilusproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curtcaldwell.nautilusproject.card.CardDetailsActivity
import com.curtcaldwell.nautilusproject.data.model.Card
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.txt_error
import kotlinx.android.synthetic.main.activity_single_card.*


interface CardClickListener {


    fun onCardClick(card: Card, imageView: ImageView)


}

class CardListActivity : AppCompatActivity() {

    private lateinit var viewModel: CardViewModel
    private lateinit var cardListAdapter: CardListPagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)
            .get(CardViewModel::class.java)
        initAdapter()
        initState()

//        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.explode))



    }

    private fun initAdapter() {

        cardListAdapter = CardListPagingAdapter(object : CardClickListener {
            override fun onCardClick(card: Card, imageView: ImageView) {
                Log.d("-----", "onCardClicked $card ${imageView.transitionName}")
//                val pair1: Pair<View, String> =
//                    Pair.create(imageView as View, imageView.transitionName ?: "")
//                val optionsCompat: ActivityOptionsCompat =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        this@CardListActivity ,
//                        pair1
//                    )
                val intent = Intent(this@CardListActivity, CardDetailsActivity::class.java)
                intent.putExtra("card_extra", card)
                this@CardListActivity.startActivity(intent)

            }

        }) { viewModel.retry() }
        rv_main.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
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
