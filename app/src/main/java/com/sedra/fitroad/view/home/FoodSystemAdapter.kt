package com.sedra.fitroad.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sedra.fitroad.R
import com.sedra.fitroad.data.model.Excercise
import com.sedra.fitroad.data.model.FoodSystem
import com.sedra.fitroad.databinding.AdapterInterestsBinding
import com.sedra.fitroad.databinding.AdapterRecommendedItemBinding

class FoodSystemAdapter(private val listener: (input: FoodSystem) -> Unit) :
    ListAdapter<FoodSystem, CustomViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<FoodSystem>() {
        override fun areItemsTheSame(oldItem: FoodSystem, newItem: FoodSystem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FoodSystem, newItem: FoodSystem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterRecommendedItemBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentMovie = getItem(position)
        val itemBinding = holder.binding as AdapterRecommendedItemBinding
        itemBinding.imageView8.load(R.drawable.foodsysimage)
        itemBinding.textView9.text = currentMovie.Food_System_Name
        itemBinding.root.setOnClickListener {
            listener(currentMovie)
        }
    }

}