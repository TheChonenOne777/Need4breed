package com.chertilov.matching

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.matching.databinding.DialogMatchBinding
import com.chertilov.matching.di.MatchingComponent
import javax.inject.Inject

class MatchDialogFragment : DialogFragment(R.layout.dialog_match) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MatchingViewModel by viewModels { viewModelFactory }

    private lateinit var binding: DialogMatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MatchingComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogMatchBinding.bind(view).also {
            it.close.setOnClickListener { dismiss() }
        }
        viewModel.matchedUser.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.image)
                .into(binding.image)
            binding.name.text = it.nickname
            binding.phone.text = it.phoneNumber
            binding.call.setOnClickListener { _ ->
                startActivity(
                    Intent(Intent.ACTION_DIAL)
                        .setData(Uri.parse(it.phoneNumber))
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                    null
                )
            }
        }
    }

}