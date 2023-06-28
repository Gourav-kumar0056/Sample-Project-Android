package com.gouravkumar.sampleprojectandroid.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.gouravkumar.sampleprojectandroid.R
import com.gouravkumar.sampleprojectandroid.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moveToUserListFragment()
    }

    private fun moveToUserListFragment(){
        Handler(Looper.getMainLooper()).postDelayed({
            val directions =
                SplashFragmentDirections.actionSplashToUserslist()
            findNavController(binding.root).navigate(directions)
        }, 3000)
    }
}