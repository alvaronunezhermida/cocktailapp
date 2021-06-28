package com.alvaronunez.cocktailapp.ui.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.common.basicDiffUtil
import com.alvaronunez.cocktailapp.ui.common.inflate
import com.alvaronunez.domain.models.Drink
import com.bumptech.glide.Glide

class DrinksAdapter :
    RecyclerView.Adapter<DrinksAdapter.ViewHolder>() {

    var drinks: List<Drink> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_drink, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = drinks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink = drinks[position]
        holder.bind(drink)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(drink: Drink) {
            //TODO: use viewBinding
            itemView.findViewById<TextView>(R.id.drinkName).text = drink.name
            Glide.with(itemView.context)
                .load(Uri.parse(drink.thumb))
                .into(itemView.findViewById(R.id.drinkThumb))
        }
    }
}