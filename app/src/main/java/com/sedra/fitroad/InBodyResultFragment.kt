package com.sedra.fitroad

import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.helper.showToast
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.databinding.FragmentInBodyResultBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InBodyResultFragment : Fragment() {

    var binding: FragmentInBodyResultBinding? = null
    val viewModel: StartingViewModel by activityViewModels()
    private var progressDialog: Dialog? = null
    @Inject
    lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_in_body_result, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
        binding?.apply {
            include.progressBar.progress = 7
            include.textView5.text = "STEP 7 OF 7"
            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }

            textView8.text = "Last inBody Date : 04/05/2015\n" +
                    "TBW : 27.2\n" +
                    "Protien : 7.1\n" +
                    "Mineral : 2.74\n" +
                    "Body Fat Mass : 22.1\n" +
                    "Weight : 59.1\n" +
                    "BMI : 24.0\n" +
                    "Percent Body Fat (%) : 37.5\n" +
                    "InBody Score : 66\n" +
                    "Weight Control : -6.2 kg\n" +
                    "Impedance : 0"
            button.setOnClickListener {
                viewModel.register()
            }
        }
    }

    private fun observeViewModels() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.signupFlow.collect {
                decideProgressVisibility(it is Resource.Loading)
                when (it) {
                    is Resource.Success      -> saveUser(it.data)
                    is Resource.Error        -> showToast(it.msg ?: "")
                    is Resource.NetworkError -> showToast("Network Error")
                    is Resource.ServerError  -> showToast("Server Error")
                    else                     -> {}
                }
            }
        }

    }

    private fun saveUser(data: SignUpResponse) {
        val gson = Gson()
        val hashMapString = gson.toJson(data.data)
        val editor = preferences.edit()
        editor.putString("user", hashMapString)
        editor.apply()
        findNavController().navigate(R.id.action_inBodyResultFragment_to_homeFragment)
    }

    private fun decideProgressVisibility(b: Boolean) {
        if (b){
            showProgressBar()
        }else{
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