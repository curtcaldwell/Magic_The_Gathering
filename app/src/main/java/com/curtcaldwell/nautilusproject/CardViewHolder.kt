package com.curtcaldwell.nautilusproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.curtcaldwell.nautilusproject.data.model.Card
import com.squareup.picasso.Picasso

class CardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val cardImage = view.findViewById<ImageView>(R.id.card_image)

    fun bind(card: Card?) {
        if (card != null) {
            Picasso.get().load(card.imageUrl)
                .error(R.drawable.ic_broken_image_black_24dp)
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(cardImage)
        }
    }

    companion object {
        fun create(parent: ViewGroup): CardViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
            return CardViewHolder(view)
        }
    }

}