package com.sedra.fitroad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sedra.fitroad.data.model.FoodSystem
import com.sedra.fitroad.databinding.FragmentExerciseDetailsBinding
import com.sedra.fitroad.databinding.FragmentFoodSystemsBinding


class FoodSystemsFragment : Fragment() {
    var binding: FragmentFoodSystemsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_food_systems, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val system = arguments?.getParcelable("food") as FoodSystem?
        binding?.apply {
            foodContents.text = system?.Food_System_Content
            foodName.text = system?.Food_System_Name
        }
    }


}