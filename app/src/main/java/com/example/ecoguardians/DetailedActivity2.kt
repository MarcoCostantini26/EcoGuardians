package com.example.ecoguardians

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailedActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed2)

        val animal = intent.getParcelableExtra<Animal>("animal")
        if(animal != null){
            val textView : TextView = findViewById(R.id.detailedActivityTV2)
            val imageView : ImageView = findViewById(R.id.detailedActivityIV2)

            textView.text = animal.name
            imageView.setImageResource(animal.image)

        }
    }
}