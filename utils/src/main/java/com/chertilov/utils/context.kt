package com.chertilov.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass


inline fun <reified T : Activity> Context.start(init: Intent.() -> Unit = {}) =
    startActivity(T::class, init)

inline fun Context.startActivity(klass: KClass<*>, init: Intent.() -> Unit = {}) =
    startActivity(Intent(this, klass.java).apply(init))


fun <T : View?> View.bind(@IdRes idRes: Int): Lazy<T> = unsafeLazy { findViewById<T>(idRes) as T }

fun <T : View?> Fragment.bind(@IdRes idRes: Int): Lazy<T> =
    unsafeLazy { requireActivity().findViewById<T>(idRes) as T }

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

val Context.inputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.makeCall(phoneNumber: String) {
    startActivity(
        Intent(Intent.ACTION_DIAL)
            .setData(Uri.parse(phoneNumber))
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        null
    )
}
