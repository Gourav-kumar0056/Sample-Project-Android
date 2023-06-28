package com.gouravkumar.sampleprojectandroid.remote

import com.gouravkumar.sampleprojectandroid.local.dao.UserDetailDao
import com.gouravkumar.sampleprojectandroid.model.Result
import com.gouravkumar.sampleprojectandroid.model.UserDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(private val userDetailDao: UserDetailDao,
                                                private val userRemoteDataSource: UserRemoteDataSource
) {

    fun fetchUserDetail(login : String) : Flow<Result<UserDetail>>{
        return flow {
            emit(fetchUserCached(login))
            emit(Result.loading())

            val result = userRemoteDataSource.fetchUserDetail(login)

            //Cache to database if response is successful
            if (result.status === Result.Status.SUCCESS) {
                result.data?.let { userDetailDao.insertUserDetail(it) }
                Result.success(result.data)
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchUserCached(login: String) : Result<UserDetail>{
        val usersData = userDetailDao.getUserDetail(login)
        return Result.success(usersData)
    }
}