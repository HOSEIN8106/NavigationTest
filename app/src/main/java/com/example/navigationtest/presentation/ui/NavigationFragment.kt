package com.example.navigationtest.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.navigationtest.R
import com.example.navigationtest.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
lateinit var binding: FragmentNavigationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_navigation,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
}