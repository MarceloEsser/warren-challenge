package com.example.warrenchallenge.persistence

object SessionManager {

    var accessToken: String?
        get() {
            return PreferencesManager._accessToken
        }
        set(value) {
            PreferencesManager._accessToken = value
        }

}