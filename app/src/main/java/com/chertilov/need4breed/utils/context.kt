package com.chertilov.need4breed.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.annotation.IdRes
import kotlin.reflect.KClass


inline fun <reified T : Activity> Context.start(init: Intent.() -> Unit = {}) =
    startActivity(T::class, init)

inline fun Context.startActivity(klass: KClass<*>, init: Intent.() -> Unit = {}) =
    startActivity(Intent(this, klass.java).apply(init))


fun <T : View?> View.bind(@IdRes idRes: Int): Lazy<T> = unsafeLazy { findViewById<T>(idRes) as T }

fun <T : View?> Activity.bind(@IdRes idRes: Int): Lazy<T> =
    unsafeLazy { findViewById<T>(idRes) as T }

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)