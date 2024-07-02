package com.sedra.fitroad.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.StartingViewModel
import com.sedra.fitroad.databinding.FragmentStageOneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StageOneFragment : Fragment() {

    private var binding: FragmentStageOneBinding? = null
    private val viewModel: StartingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_stage_one, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            imageView5.setOnClickListener {
                maleSelector.visibility = View.VISIBLE
                femaleSelector.visibility = View.GONE
                viewModel.gender = "Male"
            }
            imageView6.setOnClickListener {
                femaleSelector.visibility = View.VISIBLE
                maleSelector.visibility = View.GONE
                viewModel.gender = "Female"
            }
            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }
            button.setOnClickListener {
                findNavController().navigate(R.id.action_stageOneFragment_to_stageTwoFragment)
            }
        }
    }
}