package com.example.ecoguardians

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val animalShowcaseList:ArrayList<AnimalShowcase>, private val itemClick : ItemClickListener)
    : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(){


    class AnimalViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val textView : TextView = itemView.findViewById(R.id.textView)
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

        holder.itemView.setOnClickListener{
            itemClick.onItemClick(animalShowcaseList[position])
        }
    }
    interface ItemClickListener {
        fun onItemClick(animalShowcase : AnimalShowcase)
    }
}