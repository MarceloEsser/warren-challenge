package com.example.warrenchallenge.model

import com.google.gson.annotations.SerializedName

class UserLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)