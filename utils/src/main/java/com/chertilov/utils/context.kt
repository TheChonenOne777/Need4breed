package com.chertilov.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
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