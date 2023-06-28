package com.gouravkumar.sampleprojectandroid.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gouravkumar.sampleprojectandroid.model.UserDetail

@Dao
interface UserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetail(userDetail: UserDetail)

    @Query("SELECT * FROM UserDetail WHERE login = :login_")
    fun getUserDetail(login_ : String) : UserDetail

}