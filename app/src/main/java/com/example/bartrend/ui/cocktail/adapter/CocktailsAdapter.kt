package com.example.bartrend.ui.cocktail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bartrend.R
import com.example.bartrend.ui.cocktail.model.CocktailModel

class CocktailsAdapter(private val cocktails: List<CocktailModel>):
    RecyclerView.Adapter<CocktailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.template_cocktail, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = cocktails[position]

        holder.apply {
            cocktailTextView.text = cocktail.name
            cocktailImageView.setImageBitmap(cocktail.image)
        }
    }

    override fun getItemCount(): Int = cocktails.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cocktailTextView: TextView = itemView.findViewById(R.id.cocktail_name)
        val cocktailImageView: ImageView = itemView.findViewById(R.id.cocktail_image)
    }
}