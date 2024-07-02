package com.sedra.fitroad.view.trainers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.model.ExerciseResponse
import com.sedra.fitroad.data.model.FoodSystemsResponse
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.data.model.TrainerResponse
import com.sedra.fitroad.data.repo.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainersViewModel @Inject constructor(
    private val repo: DataRepo
) : ViewModel() {


    private val _trainerFlow: MutableStateFlow<Resource<TrainerResponse>> =
        MutableStateFlow(Resource.Idle)


    fun getTrainers() {
        _trainerFlow.value = Resource.Loading
        viewModelScope.launch {
            val response = repo.getTrainers()
            _trainerFlow.value = response
        }
    }

    val trainerFlow = _trainerFlow

}