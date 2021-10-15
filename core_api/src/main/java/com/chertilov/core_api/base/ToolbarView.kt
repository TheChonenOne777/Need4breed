package com.chertilov.core_api.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.chertilov.core_api.R
import com.chertilov.core_api.databinding.LayoutToolbarBinding

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    //    var backListener: OnBackClickListener? = null
    var menuListener: OnMenuClickListener? = null

    private var binding = LayoutToolbarBinding.inflate(LayoutInflater.from(context), this)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolbarView)
        try {
            typedArray.getBoolean(R.styleable.ToolbarView_has_back_btn, false).let {
                if (it) {
                    binding.back.setOnClickListener { findNavController().navigateUp() }
                    binding.back.isVisible = it
                }
            }
            typedArray.getResourceId(R.styleable.ToolbarView_menu_icon, -1).let {
                if (it > 0) {
                    with(binding.menu) {
                        setOnClickListener { menuListener?.onMenuClick() }
                        setImageResource(it)
                        isVisible = true
                    }
                }
            }
        } finally {
            typedArray.recycle()
        }
    }

    fun setTitle(@StringRes title: Int) {
        binding.title.setText(title)
        binding.title.isVisible = true
    }

    fun setTitle(title: String) {
        binding.title.text = title
        binding.title.isVisible = true
    }

    fun interface OnBackClickListener {
        fun onBackClick()
    }

    fun interface OnMenuClickListener {
        fun onMenuClick()
    }
}