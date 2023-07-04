package com.example.dogdetailsapp.networkUtilities

import com.example.dogdetailsapp.BASE_URL
import com.example.dogdetailsapp.END_POINT
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DogBreedDataService {

    @GET(END_POINT)
    fun getData(): Call<MutableList<DogData>>
}

val api by lazy {

    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogBreedDataService::class.java)
}
