package com.example.ecoguardians

data class AnimalShowcase(val image:Int, val name: String, val numberSpecies: String,
    val classification: String, val position: String, val averageLife: String,
    val description: String, val threats: String, val whatYouCanDo: String, val seriousLink: String) {
    constructor(image: Int, name: String) : this(
        image = image,
        name = name,
        numberSpecies = "",
        classification = "",
        position = "",
        averageLife = "",
        description = "",
        threats = "",
        whatYouCanDo = "",
        seriousLink = ""
    )
}