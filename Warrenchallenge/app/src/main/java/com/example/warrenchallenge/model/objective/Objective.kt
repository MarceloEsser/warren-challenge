package com.example.warrenchallenge.model.objective

import com.google.gson.annotations.SerializedName

class Objective(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("totalBalance")
    val totalBalance: Double,
    @SerializedName("goalAmount")
    val goalAmount: Double,
    @SerializedName("goalDate")
    val goalDate: String,
    @SerializedName("background")
    val background: ObjectiveBackground
)