package com.gouravkumar.sampleprojectandroid.ui.userdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gouravkumar.sampleprojectandroid.model.UserDetail
import com.gouravkumar.sampleprojectandroid.remote.UserDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel  @Inject constructor(private val userDetailsRepository: UserDetailsRepository) : ViewModel(){

    private val _userDetail = MutableLiveData<Result<UserDetail>>()
    var userDetail = _userDetail


    fun fetchUserDetail(login : String) : MutableLiveData<Result<UserDetail>>{
        viewModelScope.launch {
            userDetailsRepository.fetchUserDetail(login).collect{
                if(it.data != null) {
                    userDetail.value = Result.success(it.data)
                }
            }
        }
        return userDetail
    }
}