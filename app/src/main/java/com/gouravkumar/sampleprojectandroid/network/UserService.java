package com.gouravkumar.sampleprojectandroid.network;

import com.gouravkumar.sampleprojectandroid.model.UserDetail;
import com.gouravkumar.sampleprojectandroid.model.UsersInfo;
import com.gouravkumar.sampleprojectandroid.model.UsersListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("users")
    Single<Response<List<UsersInfo>>> getUsersList();

    @GET("users/{login}")
    Response<UserDetail> getUserDetail(@Path("login")String userName);
}
