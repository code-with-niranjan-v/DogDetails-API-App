package com.example.dogdetailsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogdetailsapp.databinding.ActivityMainBinding
import com.example.dogdetailsapp.networkUtilities.DogData
import com.example.dogdetailsapp.networkUtilities.api
import javax.security.auth.callback.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), MyClickListener {
    private lateinit var binding: ActivityMainBinding
    private var listOfDogData = mutableListOf<DogData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DogBreedAdapter(listOfDogData, this)

        binding.btnLoad.setOnClickListener {
            binding.pb.visibility = View.VISIBLE
            binding.btnLoad.visibility = View.GONE

            api.getData().enqueue(object : retrofit2.Callback<MutableList<DogData>> {

                override fun onResponse(
                    call: Call<MutableList<DogData>>,
                    response: Response<MutableList<DogData>>
                ) {
                    val data = response.body() ?: mutableListOf()
                    adapter.refreshList(data)
                    binding.pb.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }

                override fun onFailure(call: Call<MutableList<DogData>>, t: Throwable) {
                    Log.e("ERROR_API", "${t.message}")
                }
            })
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    override fun onClickListener(data: DogData) {
        Log.e("OnClick", "ID:${data.id}")
        val intent = Intent(this, DogDetailsActivity::class.java)
        intent.putExtra("name", data.name)
        intent.putExtra("url", data.image.url)
        intent.putExtra("lifeSpan", data.lifeSpan)
        intent.putExtra("speciality", data.speciality)
        intent.putExtra("temperament", data.temperament)
        intent.putExtra("origin", data.origin)
        startActivity(intent)
        finish()
    }
}
