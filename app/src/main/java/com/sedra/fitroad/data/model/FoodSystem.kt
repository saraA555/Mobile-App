package com.sedra.fitroad.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodSystem(
    val Food_System_Content: String?,
    val Food_System_Name: String?,
    val For_Over_Weight: String?,
    val Meals_Number: String?,
    val Total_Day_Calories: String?,
    val created_at: String?,
    val id: Int?,
    val updated_at: String?
):Parcelable