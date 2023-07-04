package com.example.dogdetailsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dogdetailsapp.databinding.ActivityDogDetailsBinding

class DogDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDogDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
    }

    private fun getData(): List<String?> {
        val name = intent.getStringExtra("name")
        var origin = intent.getStringExtra("origin") ?: "Unknown"
        if (origin.isBlank()) {
            origin = "Unknown"
        }
        val lifeSpan = intent.getStringExtra("lifeSpan")
        val speciality = intent.getStringExtra("speciality")
        val url = intent.getStringExtra("url")
        val temperament = intent.getStringExtra("temperament")

        return listOf(name, origin, lifeSpan, speciality, url, temperament)
    }

    private fun loadData() {
        val data = getData()
        binding.tvDogName2.text = data[0]
        binding.tvOriginData.text = data[1]
        binding.tvLifeSpanData.text = data[2]
        binding.tvSpecialityData.text = data[3]
        Glide.with(this).load(data[4]).into(binding.imgViewDog)
        binding.tvTemperamentData.text = data[5]
    }
}
