package com.sedra.fitroad.data.model

data class ExerciseResponse(
    val `data`: List<Excercise>?,
    val message: String?,
    val success: Boolean?
)