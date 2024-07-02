package com.sedra.fitroad.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.model.ExerciseResponse
import com.sedra.fitroad.data.model.LoginResponse
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.data.repo.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: DataRepo
) : ViewModel() {


    private val _loginFlow: MutableStateFlow<Resource<LoginResponse>> =
        MutableStateFlow(Resource.Idle)


    fun login(email:String, password:String) {
        _loginFlow.value = Resource.Loading
        viewModelScope.launch {
            val response = repo.login(
                email,
                password,

            )
            _loginFlow.value = response
        }
    }

    val loginFlow = _loginFlow

}