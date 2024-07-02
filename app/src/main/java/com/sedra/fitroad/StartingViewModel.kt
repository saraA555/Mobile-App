package com.sedra.fitroad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.data.repo.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingViewModel @Inject constructor(
    private val repo: DataRepo
) : ViewModel() {
    var gender = "Male"
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var password_confirmation: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var country: String = ""
    var gov: String = ""
    var city: String = ""
    var age: String = ""


    private val _signupFlow: MutableStateFlow<Resource<SignUpResponse>> =
        MutableStateFlow(Resource.Idle)


    fun register() {
        _signupFlow.value = Resource.Loading
        viewModelScope.launch {
            val response = repo.signUp(
                name,
                email,
                password,
                password_confirmation,
                first_name,
                last_name,
                gender,
                country,
                gov,
                city,
                age
            )
            _signupFlow.value = response
        }
    }

    val signupFlow = _signupFlow

}