package com.sedra.fitroad.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trainer(
    val Age: String?,
    val Exercise_Specialties: String?,
    val Gender: String?,
    val Name: String?,
    val Phone_Number: String?,
    val created_at: String?,
    val id: Int?,
    val start_work_date: String?,
    val updated_at: String?
): Parcelable