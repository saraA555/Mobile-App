package com.sedra.fitroad.data.model

data class TrainerResponse(
    val `data`: List<Trainer>?,
    val message: String?,
    val success: Boolean?
)