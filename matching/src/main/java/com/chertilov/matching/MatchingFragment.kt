package com.chertilov.matching

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chertilov.core_api.dto.User
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.MatchingMediator
import com.chertilov.matching.databinding.FragmentMatchingBinding
import com.chertilov.matching.di.EagerTrigger
import com.chertilov.matching.di.MatchingComponent
import com.chertilov.utils.unsafeLazy
import javax.inject.Inject

class MatchingFragment : Fragment(R.layout.fragment_matching) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eagerTrigger: EagerTrigger

    @Inject
    lateinit var matchingMediator: MatchingMediator

    private val viewModel: MatchingViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentMatchingBinding

    private val adapter by unsafeLazy { UsersAdapter { viewModel.onMatchClicked(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MatchingComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchingBinding.bind(view).also {
            it.appbar.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            it.appbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            it.matchingRecycler.adapter = adapter
        }
        viewModel.users.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.matchedUser.observe(viewLifecycleOwner) { handleMatch() }
    }

    private fun handleMatch() {
        findNavController().navigate(MatchingFragmentDirections.showMatchDialog())
    }
}