package com.chertilov.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun View.hideKeyboard() {
    context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}

fun View.showKeyboard() {
    requestFocus()
    run { context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT) }
}
