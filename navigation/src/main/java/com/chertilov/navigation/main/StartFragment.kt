package com.chertilov.navigation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.navigation.R
import javax.inject.Inject


class StartFragment : Fragment() {

    @Inject
    lateinit var dogsMediator: DogsMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
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
        // decide where to go on the first app launch, check auth tokens if login needed etc...
        dogsMediator.openDogsFlow(requireActivity())
//        (0..1).random().let {
//            when (it) {
//                0 -> (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.HomeFlow)
//                1 -> (requireActivity() as ToFlowNavigatable).navigateToFlow(
//                    NavigationFlow.DashboardFlow(
//                        "From start fragment"
//                    )
//                )
//            }
//        }
    }
}