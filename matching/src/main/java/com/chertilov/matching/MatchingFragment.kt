package com.chertilov.matching

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.dto.User
import com.chertilov.core_api.mediators.AppWithFacade
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
            with(it.appbar.toolbar) {
                setNavigationOnClickListener { requireActivity().onBackPressed() }
                setNavigationIcon(R.drawable.ic_arrow_back)
                menu.clear()
                inflateMenu(R.menu.menu_matching_switch)
                setOnMenuItemClickListener {
                    if (it.itemId == R.id.switch_matching) {
                        findNavController().navigate(MatchingFragmentDirections.switchToMatchingCardsFragment())
                    }
                    it.itemId == R.id.switch_matching
                }
            }
            it.matchingRecycler.adapter = adapter
            it.refresh.setColorSchemeResources(R.color.colorAction)
            it.refresh.setOnRefreshListener { viewModel.onRefresh() }
        }
        viewModel.users.observe(viewLifecycleOwner) { handleUsers(it) }
        viewModel.matchedUser.observe(viewLifecycleOwner) { handleMatch() }
    }

    private fun handleUsers(result: Response<List<User>>) {
        binding.progress.isVisible = result is Response.Loading
        if (result is Response.Success) {
            adapter.submitList(result.value)
            binding.refresh.isRefreshing = false
        }
    }

    private fun handleMatch() {
        findNavController().navigate(MatchingFragmentDirections.showMatchDialog())
    }
}