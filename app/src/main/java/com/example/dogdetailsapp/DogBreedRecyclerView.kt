package com.example.dogdetailsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.dogdetailsapp.networkUtilities.DogData

class DogBreedViewHolder(itemView: View) : ViewHolder(itemView) {

    private var tvDogName = itemView.findViewById<TextView>(R.id.tvDogName)
    private var imgView = itemView.findViewById<ImageView>(R.id.imgView)
    private var tvLifeSpan = itemView.findViewById<TextView>(R.id.tvLifeSpan)

    fun bindData(data: DogData, listener: MyClickListener) {
        itemView.setOnClickListener {
            listener.onClickListener(data)
        }

        tvDogName.text = data.name
        tvLifeSpan.text = "Life Span ${data.lifeSpan}"

        Glide.with(itemView).load(data.image.url).into(imgView)
    }
}

class DogBreedAdapter(
    private var listOfDogData: MutableList<DogData>,
    private var listener: MyClickListener
) : RecyclerView.Adapter<DogBreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_items, parent, false)
        return DogBreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfDogData.size
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        holder.bindData(listOfDogData[position], listener)
    }

    fun refreshList(data: MutableList<DogData>) {
        listOfDogData = data
        notifyDataSetChanged()
    }
}

interface MyClickListener {
    fun onClickListener(data: DogData)
}
