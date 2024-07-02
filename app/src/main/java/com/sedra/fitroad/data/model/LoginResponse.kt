package com.sedra.fitroad.data.model

data class LoginResponse(
    val success: Boolean?,
    val message: String?,
    val data: LoginObject?
)

data class LoginObject(
    val ID: Int?
)