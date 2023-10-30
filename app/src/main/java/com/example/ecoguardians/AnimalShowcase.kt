package com.example.ecoguardians

data class AnimalShowcase(val image:Int, val name: String, val numberSpecies: String,
    val classification: String, val position: String, val averageLife: String,
    val description: String, val threats: String, val whatYouCanDo: String, val seriousLink: String,
    val favorite: Boolean, val latitude: Double, val longitude: Double) {
    constructor(image: Int, name: String, favorite: Boolean) : this(
        image = image,
        name = name,
        numberSpecies = "",
        classification = "",
        position = "",
        averageLife = "",
        description = "",
        threats = "",
        whatYouCanDo = "",
        seriousLink = "",
        favorite = favorite,
        latitude = 0.0,
        longitude = 0.0
    )
}
