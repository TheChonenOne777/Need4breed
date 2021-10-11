package com.chertilov.auth

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chertilov.auth.databinding.FragmentPhoneNumberBinding
import com.chertilov.auth.di.EagerTrigger
import com.chertilov.auth.di.LoginComponent
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.mediators.AppWithFacade
import com.chertilov.utils.hideKeyboard
import com.redmadrobot.inputmask.MaskedTextChangedListener

import javax.inject.Inject

class PhoneNumberFragment : Fragment(R.layout.fragment_phone_number) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eagerTrigger: EagerTrigger

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentPhoneNumberBinding

    private val textListener = object : MaskedTextChangedListener.ValueListener {
        override fun onTextChanged(
            maskFilled: Boolean,
            extractedValue: String,
            formattedValue: String
        ) {
            viewModel.savePhoneNumber(extractedValue)
            binding.apply.isEnabled = maskFilled
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhoneNumberBinding.bind(view)
        viewModel.loginResult.observe(viewLifecycleOwner) { handleResult(it) }
        binding.apply.setOnClickListener { applyPhoneNumber() }
        binding.input.apply {
            setText(viewModel.phoneNumber)
            addTextChangedListener(
                MaskedTextChangedListener(
                    "(9[00])[000]-[00]-[00]",
                    this,
                    textListener
                )
            )
            setOnEditorActionListener { _, id, event ->
                if (text.length == TOTAL_LENGTH
                    && (id == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
                ) {
                    applyPhoneNumber()
                    false
                } else {
                    true
                }
            }
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && text.toString() == " ...") {
                    setText("")
                }
            }
        }
        binding.apply.isEnabled = binding.input.text.length == TOTAL_LENGTH
    }

    private fun applyPhoneNumber() {
        binding.input.hideKeyboard()
        binding.errorText.isVisible = false
        viewModel.onPhoneNumberEnter()
    }

    private fun handleResult(result: Response<String>) {
        binding.progressBar.isVisible = result is Response.Loading
        binding.arrow.isVisible = result !is Response.Loading
        binding.errorText.isVisible = result is Response.Failure
        when (result) {
            is Response.Failure -> binding.errorText.text = result.message
            is Response.Success -> {
                findNavController().navigate(PhoneNumberFragmentDirections.actionPhoneNumberFragmentToCodeFragment())
            }
        }
    }

    companion object {
        private const val TOTAL_LENGTH = 14
    }
}