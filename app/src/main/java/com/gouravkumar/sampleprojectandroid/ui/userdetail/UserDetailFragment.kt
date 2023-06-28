package com.gouravkumar.sampleprojectandroid.ui.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gouravkumar.sampleprojectandroid.R
import com.gouravkumar.sampleprojectandroid.databinding.FragmentUserDetailBinding
import com.gouravkumar.sampleprojectandroid.model.UserDetail
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private lateinit var binding : FragmentUserDetailBinding
    private lateinit var userName : String
    private val viewModel by lazy { ViewModelProvider(this).get(UserDetailViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

         if (this::userName.isInitialized) {
             getUserDetail(userName)
         }

    }

    private fun init(){
        userName = UserDetailFragmentArgs.fromBundle(requireArguments()).userName
    }

    private fun getUserDetail(login : String){
         viewModel.fetchUserDetail(login).observe(requireActivity(), Observer {


             if(it.isSuccess){

             }
             else if(it.isFailure){

             }
         })
    }

   private fun setupViews(userDetail: UserDetail){
        Glide.with(requireContext())
            .load(userDetail.avatar_url)
            .placeholder(R.drawable.ic_broken_image)
            .into(binding.userImgV)

        binding.apply {
            nameTv.text = userDetail.name
            usernameTv.text = userDetail.twitter_username
            locationTv.text = userDetail.location
            followerTv.text = userDetail.followers.toString()
            followingTv.text = userDetail.following.toString()
        }
    }

}