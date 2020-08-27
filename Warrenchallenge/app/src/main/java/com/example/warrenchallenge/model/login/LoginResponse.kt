package com.example.warrenchallenge.model.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse (
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null,
)