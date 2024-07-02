package com.sedra.fitroad.view.exercise

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
import com.sedra.fitroad.databinding.AdapterExerciseBinding
import com.sedra.fitroad.databinding.AdapterRecommendedItemBinding
import com.sedra.fitroad.databinding.AdapterTrainerBinding

class CustomViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
class ExerciseAdapter(private val listener: (input: Excercise) -> Unit) :
    ListAdapter<Excercise, CustomViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Excercise>() {
        override fun areItemsTheSame(oldItem: Excercise, newItem: Excercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Excercise, newItem: Excercise): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterExerciseBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentMovie = getItem(position)
        val itemBinding = holder.binding as AdapterExerciseBinding
        itemBinding.trainerName.text = currentMovie.ExerciseName
        itemBinding.trainerPhone.text = currentMovie.Duration
        itemBinding.trainerSpecialist.text = currentMovie.Target_Body_Area
        itemBinding.root.setOnClickListener {
            listener(currentMovie)
        }
    }

}