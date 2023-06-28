package com.gouravkumar.sampleprojectandroid.model;

import java.util.List;

public class UsersListResponse {
    List<UsersInfo> result;

    public UsersListResponse(List<UsersInfo> usersList) {
        result = usersList;
    }

    public List<UsersInfo> getResult() {
        return result;
    }

    public void setResult(List<UsersInfo> result) {
        this.result = result;
    }
}
