package com.chertilov.profile.matches

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.chertilov.core_api.dto.User
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.profile.R
import com.chertilov.profile.databinding.FragmentMatchesBinding
import com.chertilov.profile.di.ProfileComponent
import com.chertilov.utils.makeCall
import com.chertilov.utils.unsafeLazy
import javax.inject.Inject

class MatchesFragment : Fragment(R.layout.fragment_matches) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MatchesViewModel by viewModels { viewModelFactory }

    private val adapter by unsafeLazy { MatchesAdapter { showMatchAlert(it) } }

    private lateinit var binding: FragmentMatchesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchesBinding.bind(view).also {
            it.matchesRecycler.adapter = adapter
        }
        viewModel.matches.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    private fun showMatchAlert(user: User) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.call_alert_title, user.nickname))
            .setMessage(user.phoneNumber)
            .setPositiveButton(R.string.action_ok) { _, _ -> requireContext().makeCall(user.phoneNumber) }
            .setNegativeButton(R.string.action_cancel) { d, _ -> d.dismiss() }
            .create()
            .show()
    }
}