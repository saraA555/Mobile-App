package com.sedra.fitroad.view.trainers

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedra.fitroad.R
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.helper.showToast
import com.sedra.fitroad.data.model.TrainerResponse
import com.sedra.fitroad.databinding.FragmentTrainersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrainersFragment : Fragment() {
    val viewModel: TrainersViewModel by viewModels()
    var binding: FragmentTrainersBinding? = null
    private var progressDialog: Dialog? = null
    private val adapter = TrainerAdapter {
        findNavController().navigate(
            R.id.action_trainersFragment_to_trainerDetails, bundleOf(
                "trainer" to it,
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_trainers, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlow()
        binding?.apply {
            trainersRv.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
            trainersRv.adapter = adapter
            include.moreIcon.setOnClickListener {
                findNavController().navigate(R.id.action_trainersFragment_to_moreFragment)
            }
            include.homeIcon.setOnClickListener {
                findNavController().navigate(R.id.action_trainersFragment_to_homeFragment)
            }
            include.exercises.setOnClickListener {
                findNavController().navigate(R.id.action_trainersFragment_to_blankFragment)
            }
        }
        viewModel.getTrainers()
    }
    private fun observeFlow() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.trainerFlow.collect {
                decideProgressVisibility(it is Resource.Loading)
                when (it) {
                    is Resource.Success -> fillData(it.data)
                    is Resource.Error -> showToast(it.msg ?: "")
                    is Resource.NetworkError -> showToast("Network Error")
                    is Resource.ServerError -> showToast("Server Error")
                    else -> {}
                }
            }
        }
    }
    private fun fillData(data: TrainerResponse) {
        adapter.submitList(data.data)
    }
    private fun decideProgressVisibility(b: Boolean) {
        if (b) {
            showProgressBar()
        } else {
            hideProgressBar()
        }
    }

    private fun showProgressBar() {
        if (progressDialog == null) {
            progressDialog = Dialog(requireContext())
            progressDialog?.apply {
                setContentView(R.layout.dialog_progress)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
            }
        }
        progressDialog?.show()
        val window = progressDialog?.window
        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun hideProgressBar() {
        progressDialog?.dismiss()
    }


}