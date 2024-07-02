package com.sedra.fitroad.data.remote

import com.sedra.fitroad.data.model.ExerciseResponse
import com.sedra.fitroad.data.model.FoodSystemsResponse
import com.sedra.fitroad.data.model.LoginResponse
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.data.model.TrainerResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService {
    companion object {
        const val BASE_URL = "http://fitroad.coders-island.com/api/"
    }


    @Headers("Accept: application/json")
    @POST("/api/user")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @Headers("Accept: application/json")
    @POST("/api/user")
    @FormUrlEncoded
    suspend fun signup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("gender") gender: String,
        @Field("country") country: String,
        @Field("gov") gov: String,
        @Field("city") city: String,
        @Field("age") age: String,
        @Field("weight") weight: String,
        @Field("height") height: String,
        @Field("lindate") lindate: String,
        @Field("tbw") tbw: String,
        @Field("prot") prot: String,
        @Field("mineral") mineral: String,
        @Field("bfmass") bfmass: String,
        @Field("bwei") bwei: String,
        @Field("bmi") bmi: String,
        @Field("pbfat") pbfat: String,
        @Field("insco") insco: String,
        @Field("impe") impe: String,
        @Field("weco") weco: String,
    ): SignUpResponse

    @Headers("Accept: application/json")
    @GET("/api/exercises")
    suspend fun getExercises(): ExerciseResponse

    @Headers("Accept: application/json")
    @GET("/api/foodsystems")
    suspend fun getFoodSystems(): FoodSystemsResponse

    @Headers("Accept: application/json")
    @GET("/api/trainers")
    suspend fun getTrainers(): TrainerResponse
}