package com.curtcaldwell.nautilusproject.card

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.curtcaldwell.nautilusproject.R
import com.curtcaldwell.nautilusproject.data.model.Card
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single_card.*
import java.lang.Exception

class CardDetailsActivity : AppCompatActivity() {

    private lateinit var _viewmodel: CardDetailsViewModel
    private val _cardImage: ImageView by lazy { card_image }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_card)
        postponeEnterTransition()
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        val card = intent.getParcelableExtra<Card>("card_extra")
        _cardImage.transitionName = card.name
        loadImage(card)

        _viewmodel = CardDetailsViewModel(card)

        _viewmodel.name.observe(this, Observer { name ->
            card_name.text = name
        })

        _viewmodel.cardDescription.observe(this, Observer { desc ->
            card_text.text = desc
        })
        _viewmodel.cardRarity.observe(this, Observer { cardRarity ->
            card_rarity.text = cardRarity
        })

        _viewmodel.cardType.observe(this, Observer { cardType ->
            card_type.text = cardType
        })
        _viewmodel.cardSetName.observe(this, Observer { cardSetName ->
            card_setname.text = cardSetName
        })
        _viewmodel.cardArtist.observe(this, Observer { cardArtist ->
            card_artist.text = cardArtist
        })
        _viewmodel.cardFlavor.observe(this, Observer { cardFlavor ->
            card_flavor.text = cardFlavor
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun scheduleStartPostponedTransition(sharedElement: View) {
        sharedElement.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    sharedElement.viewTreeObserver.removeOnPreDrawListener(this)
                    startPostponedEnterTransition()
                    return true
                }
            })
    }

    private fun loadImage(card: Card) {
        Picasso.get().load(card.imageUrl).fit().into(_cardImage, object : Callback {
            override fun onSuccess() = scheduleStartPostponedTransition(_cardImage)
            override fun onError(e: Exception?) {
                Log.w("DetailsActivity", "Picasso failed to load $e")
            }
        })
    }
}
