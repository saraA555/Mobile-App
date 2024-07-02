package com.sedra.fitroad.view.register.hoppies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sedra.fitroad.R
import com.sedra.fitroad.databinding.FragmentStageThreeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StageThreeFragment : Fragment() {

    var binding: FragmentStageThreeBinding? = null
    val adapter = InterestsAdapter { inp -> }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_stage_three, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_stageThreeFragment_to_stageFourFragment)
            }
            include.progressBar.progress = 3
            include.textView5.text = "STEP 3 OF 7"
            val adapter = InterestsAdapter { inp -> }

            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
            recyclerView.adapter = adapter
            adapter.submitList(listOf(
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
            ))
            Log.e("TAG", "onViewCreated: ${adapter.itemCount}", )
        }
    }
}