package com.alvaronunez.cocktailapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.common.basicDiffUtil
import com.alvaronunez.cocktailapp.ui.common.inflate
import com.alvaronunez.domain.models.Ingredient

class IngredientsAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    var ingredients: List<Ingredient> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_ingredient, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient)
        holder.itemView.setOnClickListener { listener(ingredient.name) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(ingredient: Ingredient) {
            itemView.findViewById<TextView>(R.id.ingredientName).text = ingredient.name
        }
    }
}