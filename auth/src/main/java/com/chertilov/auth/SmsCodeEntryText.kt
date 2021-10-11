package com.chertilov.auth

import android.content.Context
import android.util.AttributeSet
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.chertilov.utils.getColorCompat


class SmsCodeEntryText : PinEntryEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val errorColor = context.getColorCompat(R.color.colorTextError)
    private val normalColor = context.getColorCompat(R.color.colorTextPrimary)

    /**
     * Sets up all text paints color to red if there is an error and to normal otherwise.
     * Also triggers flag for setting a line color.
     */
    fun showError(isError: Boolean) {
        val color = if (isError) errorColor else normalColor
        mCharPaint.color = color
        mSingleCharPaint.color = color
        mLastCharPaint.color = color
        setError(true)
    }
}