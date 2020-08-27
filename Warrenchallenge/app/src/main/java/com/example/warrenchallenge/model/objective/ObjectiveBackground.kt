package com.example.warrenchallenge.model.objective

import com.google.gson.annotations.SerializedName

class ObjectiveBackground(
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("raw")
    val raw: String,
)
