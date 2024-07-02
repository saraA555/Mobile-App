package com.sedra.fitroad.data.repo

import android.util.Log
import com.sedra.fitroad.data.helper.Resource
import com.sedra.fitroad.data.model.ExerciseResponse
import com.sedra.fitroad.data.model.FoodSystemsResponse
import com.sedra.fitroad.data.model.LoginResponse
import com.sedra.fitroad.data.model.SignUpResponse
import com.sedra.fitroad.data.model.TrainerResponse
import com.sedra.fitroad.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataRepo @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        password_confirmation: String,
        first_name: String,
        last_name: String,
        gender: String,
        country: String,
        gov: String,
        city: String,
        age: String,
    ): Resource<SignUpResponse> {
        return try {
            val response = apiService.signup(
                name,
                email,
                password,
                password_confirmation,
                first_name,
                last_name,
//                gender,
//                country,
//                gov,
//                city,
//                age,
                "Male",
                "Egypt",
                "Dakahlia",
                "Mansoura",
                "33",
                "130",
                "170",
                "2023-06-04",
                "27.2",
                "7.1",
                "2.74",
                "22.1",
                "125",
                "24.0",
                "37.5",
                "66",
                "5",
                "-6.2 kg"
            )
            if (response.success == true) {
                Resource.Success(response)
            } else {
                Resource.Error(response.message)
            }
        } catch (e: HttpException) {
            Resource.Error(e.message())
        } catch (e: IOException) {
            Log.e("TAG", "signUp: $e", )
            Resource.NetworkError()
        } catch (e: Exception) {
            Log.e("TAG", "signUp: $e", )
            Resource.ServerError()
        }
    }
    suspend fun getExercises(): Resource<ExerciseResponse> {
        return try {
            val response = apiService.getExercises()
            if (response.success == true) {
                Resource.Success(response)
            } else {
                Resource.Error(response.message)
            }
        } catch (e: HttpException) {
            Resource.Error(e.message())
        } catch (e: IOException) {
            Log.e("TAG", "signUp: $e", )
            Resource.NetworkError()
        } catch (e: Exception) {
            Log.e("TAG", "signUp: $e", )
            Resource.ServerError()
        }
    }
    suspend fun login(email: String, password: String): Resource<LoginResponse> {
        return try {
            val response = apiService.login(email, password)
            if (response.success == true) {
                Resource.Success(response)
            } else {
                Resource.Error(response.message)
            }
        } catch (e: HttpException) {
            Resource.Error(e.message())
        } catch (e: IOException) {
            Log.e("TAG", "signUp: $e", )
            Resource.NetworkError()
        } catch (e: Exception) {
            Log.e("TAG", "signUp: $e", )
            Resource.ServerError()
        }
    }
    suspend fun getFoodSystems(): Resource<FoodSystemsResponse> {
        return try {
            val response = apiService.getFoodSystems()
            if (response.success == true) {
                Resource.Success(response)
            } else {
                Resource.Error(response.message)
            }
        } catch (e: HttpException) {
            Resource.Error(e.message())
        } catch (e: IOException) {
            Log.e("TAG", "signUp: $e", )
            Resource.NetworkError()
        } catch (e: Exception) {
            Log.e("TAG", "signUp: $e", )
            Resource.ServerError()
        }
    }
    suspend fun getTrainers(): Resource<TrainerResponse> {
        return try {
            val response = apiService.getTrainers()
            if (response.success == true) {
                Resource.Success(response)
            } else {
                Resource.Error(response.message)
            }
        } catch (e: HttpException) {
            Resource.Error(e.message())
        } catch (e: IOException) {
            Log.e("TAG", "signUp: $e", )
            Resource.NetworkError()
        } catch (e: Exception) {
            Log.e("TAG", "signUp: $e", )
            Resource.ServerError()
        }
    }
}