package com.example.ecoguardians

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private var animalShowcaseList:ArrayList<AnimalShowcase>, private val itemClick : ItemClickListener,
                    private val favoriteClick: ItemClickListener, isFavorite: ArrayList<Boolean>)
    : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(){

    private var favoriteIcon : ArrayList<Boolean> = isFavorite

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

        if(favoriteIcon[position]) {
            holder.favoriteView.setImageResource(R.drawable.favorite_fill_icon)
        }else {
            holder.favoriteView.setImageResource(R.drawable.favorite_icon)
        }

        holder.favoriteView.setOnClickListener {
            favoriteClick.toogleFavoriteState(holder.favoriteView, animalShowcaseList[position])
        }

        holder.itemView.setOnClickListener{
            itemClick.onItemClick(animalShowcaseList[position])
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(filteredList: List<AnimalShowcase>, updatedFavorites: List<Boolean>) {
        animalShowcaseList = filteredList as ArrayList<AnimalShowcase>
        favoriteIcon = updatedFavorites as ArrayList<Boolean>
        notifyDataSetChanged()
    }

    fun updateFavorites(updatedFavorites: List<Boolean>) {
        favoriteIcon = updatedFavorites as ArrayList<Boolean>
    }

    interface ItemClickListener {
        fun onItemClick(animalShowcase : AnimalShowcase)
        fun toogleFavoriteState(btnFavorite: ImageButton, animalShowcase: AnimalShowcase)
    }
}
