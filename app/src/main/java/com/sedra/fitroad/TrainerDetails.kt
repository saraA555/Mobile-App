package com.sedra.fitroad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sedra.fitroad.data.model.Trainer
import com.sedra.fitroad.databinding.FragmentTrainerDetailsBinding


class TrainerDetails : Fragment() {
    var binding: FragmentTrainerDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_trainer_details, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trainer = arguments?.getParcelable("trainer") as Trainer?
        binding?.apply {
            name.text = trainer?.Name
            textView17.text = trainer?.Phone_Number
            textView18.text = trainer?.Exercise_Specialties
        }
    }


}