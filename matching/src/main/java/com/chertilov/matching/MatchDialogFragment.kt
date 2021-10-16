package com.chertilov.matching

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.matching.databinding.DialogMatchBinding
import com.chertilov.matching.di.MatchingComponent
import com.chertilov.utils.makeCall
import javax.inject.Inject

class MatchDialogFragment : DialogFragment(R.layout.dialog_match) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MatchingViewModel by viewModels { viewModelFactory }

    private lateinit var binding: DialogMatchBinding

    private val args: MatchDialogFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MatchingComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        viewModel.onMatchDialogOpened(args.phoneNumber)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogMatchBinding.bind(view).also {
            it.close.setOnClickListener { dismiss() }
        }
        viewModel.matchedUser.observe(viewLifecycleOwner) {
            if (it != null) {
                Glide.with(requireContext())
                    .load(it.image)
                    .into(binding.image)
                binding.name.text = it.nickname
                binding.phone.text = it.phoneNumber
                binding.call.setOnClickListener { _ -> requireContext().makeCall(it.phoneNumber) }
            }
        }
    }

}