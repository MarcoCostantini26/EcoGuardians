package com.example.ecoguardians

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val animalShowcaseList:ArrayList<AnimalShowcase>, private val itemClick : ItemClickListener)
    : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(){

    private var isFavorite = false

    class AnimalViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val textView : TextView = itemView.findViewById(R.id.textView)
        val favoriteView : ImageButton = itemView.findViewById(R.id.favorite_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animalShowcaseList.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalShowcaseList[position]
        holder.imageView.setImageResource(animal.image)
        holder.textView.text = animal.name

        // Creare un OnClickListener per il pulsante del preferito
        val favoriteClickListener = View.OnClickListener {
            toggleFavoriteState(holder.favoriteView)
        }

        holder.favoriteView.setOnClickListener(favoriteClickListener)

        holder.itemView.setOnClickListener{
            itemClick.onItemClick(animalShowcaseList[position])
        }
    }


    private fun toggleFavoriteState(btnFavorite: ImageButton) {
        isFavorite = !isFavorite

        // Cambia l'icona del preferito in base allo stato attuale
        val iconResource = if (isFavorite) R.drawable.favorite_fill_icon else R.drawable.favorite_icon
        val favoriteDrawable = AppCompatResources.getDrawable(btnFavorite.context, iconResource)
        btnFavorite.setImageDrawable(favoriteDrawable)

        // TODO tenere traccia in un elenco la lista dei preferiti
    }

    interface ItemClickListener {
        fun onItemClick(animalShowcase : AnimalShowcase)
    }
}