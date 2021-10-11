package com.chertilov.navigation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.core_api.mediators.LoginMediator
import com.chertilov.navigation.R
import javax.inject.Inject


class StartFragment : Fragment() {

    @Inject
    lateinit var dogsMediator: DogsMediator

    @Inject
    lateinit var loginMediator: LoginMediator

    @Inject
    lateinit var sessionPreferences: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (sessionPreferences.isLoggedIn()) dogsMediator.openDogsFlow(findNavController())
        else loginMediator.openLoginFlow(findNavController())
    }
}