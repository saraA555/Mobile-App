package com.sedra.fitroad.view.register.notification

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.databinding.FragmentStageTwoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StageTwoFragment : Fragment() {
    var binding: FragmentStageTwoBinding? = null
    private val NOTIFICATION_PERMISSION_CODE = 1

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)
        } else {
            // Permission denied
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_stage_two, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            button.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }else{
                    showFakeDialog()
                }

            }

            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }
            include.progressBar.progress = 2
            include.textView5.text = "STEP 2 OF 7"
        }
    }

    private fun showFakeDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enable Notifications")
        builder.setMessage("Are you sure you want to enable notifications?")
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Perform action when OK button is clicked
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            // Perform action when Cancel button is clicked
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }

    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }else{
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)

        }
    }

}