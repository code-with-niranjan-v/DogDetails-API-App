package com.example.dogdetailsapp.networkUtilities

import com.google.gson.annotations.SerializedName

data class DogData(
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var image: DogImage,
    @SerializedName("life_span")
    var lifeSpan: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("origin")
    var origin: String,
    @SerializedName("temperament")
    var temperament: String,
    @SerializedName("bred_for")
    var speciality: String

)

data class DogImage(
    @SerializedName("url")
    var url: String
)
