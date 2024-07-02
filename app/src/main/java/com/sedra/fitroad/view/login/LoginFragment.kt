package com.sedra.fitroad.view.login

import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.sedra.fitroad.R
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.helper.showToast
import com.sedra.fitroad.data.model.LoginResponse
import com.sedra.fitroad.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var progressDialog: Dialog? = null
    val viewModel: LoginViewModel by viewModels()
    var binding: FragmentLoginBinding? = null
    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
        binding?.apply {
            login.setOnClickListener {
                viewModel.login(
                    loginEmail.text.toString(), loginPassword.text.toString()
                )
            }
        }
    }

    private fun observeViewModels() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginFlow.collect {
                decideProgressVisibility(it is Resource.Loading)
                when (it) {
                    is Resource.Success -> saveUser(it.data)
                    is Resource.Error ->         findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    is Resource.NetworkError -> showToast("Network Error")
                    is Resource.ServerError -> showToast("Server Error")
                    else -> {}
                }
            }
        }

    }
    private fun saveUser(data: LoginResponse) {
        val gson = Gson()
        val hashMapString = gson.toJson(data.data)
        val editor = preferences.edit()
        editor.putString("user", hashMapString)
        editor.apply()
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
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