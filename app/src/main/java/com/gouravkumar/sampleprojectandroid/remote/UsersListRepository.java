package com.gouravkumar.sampleprojectandroid.remote;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.gouravkumar.sampleprojectandroid.local.dao.UsersListDao;
import com.gouravkumar.sampleprojectandroid.model.Result;
import com.gouravkumar.sampleprojectandroid.model.UsersInfo;
import com.gouravkumar.sampleprojectandroid.model.UsersListResponse;
import com.gouravkumar.sampleprojectandroid.network.UserService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersListRepository {

    private UsersListDao usersListDao;
    private Retrofit retrofit;

    @Inject
    UsersListRepository(UsersListDao usersListDao, Retrofit retrofit){
        this.usersListDao = usersListDao;
        this.retrofit = retrofit;
    }

    @SuppressLint("CheckResult")
    public LiveData<List<UsersInfo>> fetchUsers() {
        UserService userService = retrofit.create(UserService.class);
        Single<Response<List<UsersInfo>>> resultObservable = userService.getUsersList();
        resultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<List<UsersInfo>>>() {

                    @Override
                    public void onSuccess(Response<List<UsersInfo>> listResponse) {
                        Result.Companion.loading(null);
                        assert listResponse.body() != null;
                        usersListDao.deleteAll(listResponse.body());
                        usersListDao.insertAll(listResponse.body());
                        Result.Companion.success(listResponse.body());
                    }

                    @Override
            public void onError(Throwable e) {
                Result.Companion.error("Error fetching users list", null);
            }
        });

        return usersListDao.getAllUsers();
    }
}
