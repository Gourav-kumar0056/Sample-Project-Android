package com.gouravkumar.sampleprojectandroid.remote

import android.annotation.SuppressLint
import com.gouravkumar.sampleprojectandroid.model.Result
import com.gouravkumar.sampleprojectandroid.model.UserDetail
import com.gouravkumar.sampleprojectandroid.network.UserService
import com.gouravkumar.sampleprojectandroid.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchUserDetail(login : String) : Result<UserDetail?> {
        val userService = retrofit.create(UserService::class.java)
        return getResponse(
            request = {userService.getUserDetail(login)},
            defaultErrorMessage = "Error fetching users detail"
        )
    }


    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T?> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }

}
