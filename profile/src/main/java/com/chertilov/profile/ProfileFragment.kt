package com.chertilov.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chertilov.auth.di.EagerTrigger
import com.chertilov.core_api.dto.User
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.core_api.mediators.LoginMediator
import com.chertilov.profile.databinding.FragmentProfileBinding
import com.chertilov.profile.di.ProfileComponent
import com.chertilov.utils.getColorCompat
import com.chertilov.utils.unsafeLazy
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eagerTrigger: EagerTrigger

    @Inject
    lateinit var dogsMediator: DogsMediator

    @Inject
    lateinit var loginMediator: LoginMediator

    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private val alertManager by unsafeLazy { ProfileAlertManager(this, viewModel) }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view).also {
            it.name.setOnClickListener { alertManager.showNameAlert() }
            it.breed.setOnClickListener { alertManager.showBreedAlert() }
            it.description.setOnClickListener { alertManager.showDescriptionAlert() }
            it.mainImage.setOnClickListener { alertManager.showImageAlert() }
            it.searchBtn.setOnClickListener { dogsMediator.openDogsFlow(findNavController()) }
            it.logout.setOnClickListener {
                viewModel.logout()
                loginMediator.openLoginFlow(findNavController())
            }
        }
        viewModel.user.observe(viewLifecycleOwner) { handleUser(it) }
        viewModel.image.observe(viewLifecycleOwner) { setMainImage(it) }
        viewModel.name.observe(viewLifecycleOwner) { setProfileField(binding.name, it) }
        viewModel.breed.observe(viewLifecycleOwner) { setProfileField(binding.breed, it) }
        viewModel.description.observe(viewLifecycleOwner) { setProfileField(binding.description, it) }
    }

    private fun handleUser(user: User) {
        setMainImage(user.image)
        setProfileField(binding.name, user.nickname)
        setProfileField(binding.breed, user.breed)
        setProfileField(binding.description, user.description)
    }

    private fun setProfileField(view: TextView, value: String) {
        if (value.isNotEmpty()) {
            view.text = value
            view.setTextColor(requireContext().getColorCompat(R.color.colorTextPrimary))
        }
    }

    private fun setMainImage(imageUrl: String) {
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.mainImage)
        binding.imagePlaceholder.isVisible = imageUrl.isEmpty()
    }
}