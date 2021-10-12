package com.chertilov.auth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chertilov.auth.databinding.FragmentCodeBinding
import com.chertilov.auth.di.EagerTrigger
import com.chertilov.auth.di.LoginComponent
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.core_api.mediators.DogsMediator
import com.chertilov.utils.hideKeyboard
import com.chertilov.utils.showKeyboard
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CodeFragment : Fragment(R.layout.fragment_code) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eagerTrigger: EagerTrigger

    @Inject
    lateinit var dogsMediator: DogsMediator

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginResult.observe(viewLifecycleOwner) { handleResult(it) }
        viewModel.timeLeft.observe(viewLifecycleOwner) { handleLeftTime(it) }
        binding = FragmentCodeBinding.bind(view).also {
            with(it.input) {
                showKeyboard()
                setOnPinEnteredListener {
                    hideKeyboard()
                    viewModel.onCodeEnter(it.toString())
                }
                doOnTextChanged { _, _, _, _ ->
                    it.errorText.isVisible = false
                    showError(false)
                }
            }
            it.resend.setOnClickListener { _ ->
                it.input.text = null
                viewModel.onResendCode()
            }
        }
    }

    private fun handleResult(result: Response<String>) {
        binding.input.isEnabled = result !is Response.Loading
        binding.errorText.isVisible = result is Response.Failure
        binding.input.showError(result is Response.Failure)
        when (result) {
            is Response.Failure -> binding.errorText.text = result.message
            is Response.Success -> dogsMediator.openDogsFlow(findNavController())
        }
    }

    private fun handleLeftTime(leftTime: Int) {
            binding.timeLeft.isVisible = leftTime != 0
            binding.timeLeft.text = getString(R.string.code_time_left_text, formatTime(leftTime))
            binding.resend.isVisible = leftTime == 0
            binding.warning.isVisible = leftTime == 0
    }

    private fun formatTime(time: Int) =
        SimpleDateFormat("mm:ss", Locale.getDefault()).format(time * 1000).substring(1)

}