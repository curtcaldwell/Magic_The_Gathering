package com.curtcaldwell.nautilusproject.card

import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionInflater
import android.util.Log
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.curtcaldwell.nautilusproject.R
import com.curtcaldwell.nautilusproject.data.model.Card
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single_card.*
import java.lang.Exception
import java.security.AccessController.getContext

class CardDetailsActivity : AppCompatActivity() {

    private lateinit var _viewmodel: CardDetailsViewModel
    private lateinit var _cardImage: ImageView
    private lateinit var _card: Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        with(window) {
//            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//            sharedElementEnterTransition = Slide()
//            sharedElementExitTransition = Slide()
//        }
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_single_card)




        _cardImage = findViewById(R.id.card_image)
        Log.d("-----", "cardDetailActivityOnCreate")
        val card = intent.getParcelableExtra<Card>("card_extra")

        _card = card

//        _cardImage.transitionName = card.name
//
//        Picasso.get().load(_card.imageUrl).noFade().fit().into(_cardImage, object : Callback {
//            override fun onSuccess() {
//                supportStartPostponedEnterTransition()
//                Log.d("-----", "FirstSuccess")
//            }
//            override fun onError(e: Exception?) {
//                supportStartPostponedEnterTransition()
//                Log.d("-----", "FirstError")
//            }
//        })

        _viewmodel = CardDetailsViewModel(card)

        _viewmodel.name.observe(this, Observer { name ->
            card_name.text = name
        })
        _viewmodel.cardImage.observe(this, Observer { cardImage ->
            Picasso.get().load(cardImage).fit().into(card_image)
        })
        _viewmodel.cardText.observe(this, Observer { cardText ->
            card_text.text = cardText

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
//        loadImage()


//        addTransitionListener()

    }

    private fun addTransitionListener(): Boolean {
        val transition = window.sharedElementEnterTransition
        if (transition != null) {
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(p0: Transition?) {
                    Log.d("-----", "onTransitionEnd")
//                    loadImage()
//                    transition.removeListener(this)
                }

                override fun onTransitionResume(p0: Transition?) {
                    Log.d("-----", "onResume")

                }

                override fun onTransitionPause(p0: Transition?) {
                    Log.d("-----", "onPause")

                }

                override fun onTransitionCancel(p0: Transition?) {
                    Log.d("-----", "onCancel")

                }

                override fun onTransitionStart(p0: Transition?) {
                    Log.d("-----", "onTransitionStart")
                }

            })
            Log.d("-----", "true")

            return true

        }
        Log.d("-----", "onfalse")

        return false

    }

    fun loadImage() {

        Picasso.get().load(_card.imageUrl).noFade().fit().into(_cardImage, object : Callback {
            override fun onSuccess() {
                supportStartPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                supportStartPostponedEnterTransition()
            }

        })
    }
}
