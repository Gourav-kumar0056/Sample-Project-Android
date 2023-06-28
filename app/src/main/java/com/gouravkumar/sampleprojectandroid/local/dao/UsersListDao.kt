package com.gouravkumar.sampleprojectandroid.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gouravkumar.sampleprojectandroid.model.UsersInfo

@Dao
interface UsersListDao {
    @Query("SELECT * FROM UsersInfo")
    fun getAllUsersList(): List<UsersInfo>

    @Query("SELECT * FROM UsersInfo")
    fun getAllUsers(): LiveData<List<UsersInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usersList: List<UsersInfo>)

    @Delete
    fun deleteAll(usersList: List<UsersInfo>)
}