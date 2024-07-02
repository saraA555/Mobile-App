package com.sedra.fitroad.view.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.model.ExerciseResponse
import com.sedra.fitroad.data.model.FoodSystemsResponse
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.data.repo.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repo: DataRepo
) : ViewModel() {


    private val _exerciseFlow: MutableStateFlow<Resource<ExerciseResponse>> =
        MutableStateFlow(Resource.Idle)

    fun getExercise() {
        _exerciseFlow.value = Resource.Loading
        viewModelScope.launch {
            val response = repo.getExercises()
            _exerciseFlow.value = response
        }
    }

    val exerciseFlow = _exerciseFlow
}