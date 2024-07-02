package com.sedra.fitroad.view.uploadinbodytwo

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.databinding.FragmentUploadInBodyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UploadInBodyFragment : Fragment() {

    private var progressDialog: Dialog? = null
    var binding: FragmentUploadInBodyBinding? = null
    var uri: Uri? = null
    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val selectedImageUri = intent?.data
                // Process the selected image URI here
                // For example, display it in an ImageView
                uri = selectedImageUri
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_upload_in_body, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            include.progressBar.progress = 6
            include.textView5.text = "STEP 6 OF 7"
            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }

            upload.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                // Start the activity to pick an image
                getContent.launch(intent)
            }
            button.setOnClickListener {
                if (uri != null) {
                    lifecycleScope.launch {
                        showProgressBar()
                        delay(10000)
                        hideProgressBar()
                        findNavController().navigate(R.id.action_uploadInBodyFragment2_to_inBodyResultTwoFragment)
                    }
                } else {
                    Toast.makeText(context, "Please, Select Image first", Toast.LENGTH_SHORT).show()
                }
            }
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