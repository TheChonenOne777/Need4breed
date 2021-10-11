package com.chertilov.base_auth

import android.content.SharedPreferences
import com.chertilov.core_api.base.DelegatedPreference
import javax.inject.Inject

class SessionPreferences @Inject constructor(pref: SharedPreferences) {

    private var session by DelegatedPreference(pref, "")

    fun clear() {
        session = ""
    }

    fun logIn(session: String) {
        this.session = session
    }

    fun isLoggedIn() = session.isNotEmpty()
}