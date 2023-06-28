package com.gouravkumar.sampleprojectandroid.ui.userslist;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gouravkumar.sampleprojectandroid.model.Result;
import com.gouravkumar.sampleprojectandroid.model.UsersInfo;
import com.gouravkumar.sampleprojectandroid.model.UsersListResponse;
import com.gouravkumar.sampleprojectandroid.remote.UsersListRepository;


import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UsersListViewmodel extends ViewModel {
    private UsersListRepository userListRepository;


    @Inject
    public UsersListViewmodel(UsersListRepository userListRepository){
         this.userListRepository = userListRepository;
    }

    public LiveData<List<UsersInfo>> fetchUsersList(){
     return userListRepository.fetchUsers();
    }

}
