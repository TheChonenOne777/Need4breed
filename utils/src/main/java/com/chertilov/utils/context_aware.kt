package com.chertilov.utils

import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.hideKeyboard() {
    context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}

fun View.showKeyboard() {
    requestFocus()
    run { context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT) }
}
