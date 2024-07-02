package com.sedra.fitroad.data.model

data class FoodSystemsResponse(
    val `data`: List<FoodSystem?>?,
    val message: String?,
    val success: Boolean?
)