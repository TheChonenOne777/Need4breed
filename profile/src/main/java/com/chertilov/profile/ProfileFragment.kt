package com.chertilov.profile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chertilov.auth.di.EagerTrigger
import com.chertilov.core_api.dto.User
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.profile.databinding.FragmentProfileBinding
import com.chertilov.profile.di.ProfileComponent
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eagerTrigger: EagerTrigger

    @Inject
    lateinit var dogsMediator: DogsMediator

    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.user.observe(viewLifecycleOwner) { handleUser(it) }
        binding = FragmentProfileBinding.bind(view).also {
            it.name.setOnClickListener { }
            it.breed.setOnClickListener { }
            it.description.setOnClickListener { }
            it.mainImage.setOnClickListener { }
        }
    }

    private fun handleUser(user: User) {
        Glide.with(requireContext())
            .load(user.image)
            .into(binding.mainImage)
        binding.name.text = user.nickname
        binding.breed.text = user.breed
        binding.description.text = user.description
    }
}