package com.sedra.fitroad.view.register.logindata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.StartingViewModel
import com.sedra.fitroad.databinding.FragmentStageFiveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StageFiveFragment : Fragment() {
    var binding : FragmentStageFiveBinding? = null
    val viewModel: StartingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_stage_five, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            button.setOnClickListener {
                if (username.text.toString().isEmpty()) {
                    Toast.makeText(context, "Please, Enter username", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (email.text.toString().isEmpty()) {
                    Toast.makeText(context, "Please, Enter email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                } else if (password.text.toString().isEmpty()) {
                    Toast.makeText(context, "Please, Enter password", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener

                } else if (confirmPassword.text.toString().isEmpty()) {
                    Toast.makeText(context, "Please, Enter password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }
                viewModel.name = username.text.toString()
                viewModel.email = email.text.toString()
                viewModel.password = password.text.toString()
                viewModel.password_confirmation = confirmPassword.text.toString()
                findNavController().navigate(R.id.action_stageFiveFragment_to_uploadInBodyFragment)

            }
            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }

            include.progressBar.progress = 5
            include.textView5.text = "STEP 5 OF 7"
        }
    }

}