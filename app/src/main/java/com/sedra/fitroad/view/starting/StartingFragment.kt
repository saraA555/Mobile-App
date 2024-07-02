package com.sedra.fitroad.view.starting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.databinding.FragmentStartingBinding

class StartingFragment : Fragment() {
    var binding: FragmentStartingBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starting, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.getStarted?.setOnClickListener{
            findNavController().navigate(R.id.action_startingFragment_to_stageOneFragment)
        }
        binding?.textView4?.setOnClickListener{
            findNavController().navigate(R.id.action_startingFragment_to_loginFragment)
        }
    }


}