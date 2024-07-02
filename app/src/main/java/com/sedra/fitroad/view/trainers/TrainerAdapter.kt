package com.sedra.fitroad.view.trainers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sedra.fitroad.R
import com.sedra.fitroad.data.model.Excercise
import com.sedra.fitroad.data.model.Trainer
import com.sedra.fitroad.databinding.AdapterRecommendedItemBinding
import com.sedra.fitroad.databinding.AdapterTrainerBinding

class CustomViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
class TrainerAdapter(private val listener: (input: Trainer) -> Unit) :
    ListAdapter<Trainer, CustomViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Trainer>() {
        override fun areItemsTheSame(oldItem: Trainer, newItem: Trainer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Trainer, newItem: Trainer): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTrainerBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentMovie = getItem(position)
        val itemBinding = holder.binding as AdapterTrainerBinding
        itemBinding.trainerName.text = currentMovie.Name
        itemBinding.trainerPhone.text = currentMovie.Phone_Number
        itemBinding.trainerSpecialist.text = currentMovie.Exercise_Specialties
        itemBinding.root.setOnClickListener {
            listener(currentMovie)
        }
    }

}