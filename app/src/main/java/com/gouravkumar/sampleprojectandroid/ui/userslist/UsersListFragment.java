package com.gouravkumar.sampleprojectandroid.ui.userslist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gouravkumar.sampleprojectandroid.R;
import com.gouravkumar.sampleprojectandroid.databinding.FragmentUsersListBinding;
import com.gouravkumar.sampleprojectandroid.model.UsersInfo;

import java.util.ArrayList;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UsersListFragment extends Fragment {
    private FragmentUsersListBinding binding;
    private UsersListViewmodel usersListViewmodel;
    private UsersListAdapter usersListAdapter;


    public UsersListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        init();
        getUsersList();
    }

    private void init(){
        usersListViewmodel = new ViewModelProvider(this).get(UsersListViewmodel.class);
    }

    private void getUsersList(){
        usersListViewmodel.fetchUsersList().observe(getViewLifecycleOwner(), usersList -> {
            if (usersList != null) {
                ArrayList<UsersInfo> usersArrayList = new ArrayList<>();
                usersArrayList.addAll(usersList);
                usersArrayList.remove(0);
                usersArrayList.remove(1);
                usersListAdapter = new UsersListAdapter(requireContext(), usersArrayList);
                binding.usersListRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
                binding.usersListRV.setAdapter(usersListAdapter);
            }
        });
    }
    }
