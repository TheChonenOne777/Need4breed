package com.chertilov.matching

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chertilov.core_api.base.ToolbarView
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.matching.databinding.FragmentMatchingCardsBinding
import com.chertilov.matching.di.MatchingComponent
import javax.inject.Inject

class MatchingCardsFragment : Fragment(R.layout.fragment_matching_cards) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MatchingViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentMatchingCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MatchingComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchingCardsBinding.bind(view).also {
            with(it.appbar) {
                menuListener = ToolbarView.OnMenuClickListener {
                    findNavController().navigate(MatchingFragmentDirections.switchToMatchingCardsFragment())
                }
            }
            it.motionLayout.setTransitionListener(object : TransitionAdapter() {

                override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                    when (currentId) {
                        R.id.offScreenPass, R.id.offScreenLike -> {
                            motionLayout.progress = 0f
                            motionLayout.setTransition(R.id.rest, R.id.like)
                            viewModel.onSwipe(currentId)
                        }
                    }
                }
            })
        }
        viewModel.swipeModel.observe(viewLifecycleOwner) { it?.let { handleNewModel(it) } }
        viewModel.matchedUser.observe(viewLifecycleOwner) { handleMatch() }
    }

    private fun handleNewModel(newModel: SwipeModel) {
        Glide.with(binding.root.context)
            .load(newModel.top.image)
            .into(binding.topImage)
        binding.topName.text = newModel.top.nickname
        binding.topBreed.text = newModel.top.breed
        binding.topDescription.text = newModel.top.description
        Glide.with(binding.root.context)
            .load(newModel.bottom.image)
            .into(binding.bottomImage)
        binding.bottomName.text = newModel.bottom.nickname
        binding.bottomBreed.text = newModel.bottom.breed
        binding.bottomDescription.text = newModel.bottom.description
    }

    private fun handleMatch() {
        findNavController().navigate(MatchingCardsFragmentDirections.showMatchDialog())
    }
}