package com.example.bartrend.ui.cocktail.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
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

    var onToggleFavorite: (Boolean) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.template_cocktail, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = cocktails[position]

        holder.apply {
            cocktailTextView.text = cocktail.name
            cocktailImageView.background = BitmapDrawable(context.resources, cocktail.image)
            cocktailFavorite.setOnClickListener {
                cocktailFavorite.setImageResource(R.drawable.icon_favorite_border
                        .takeIf { isFavorite }
                        ?: R.drawable.icon_favorite)
                isFavorite = !isFavorite
                onToggleFavorite(isFavorite)
            }
        }
    }

    override fun getItemCount(): Int = cocktails.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context: Context = itemView.context
        var isFavorite: Boolean = false

        val cocktailTextView: TextView = itemView.findViewById(R.id.cocktail_name)
        val cocktailImageView: ImageView = itemView.findViewById(R.id.cocktail_image)
        val cocktailFavorite: ImageView = itemView.findViewById(R.id.cocktail_favorite)
    }
}