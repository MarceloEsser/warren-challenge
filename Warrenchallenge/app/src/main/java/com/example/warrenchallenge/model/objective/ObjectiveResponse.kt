package com.example.warrenchallenge.model.objective

import com.google.gson.annotations.SerializedName

class ObjectiveResponse(
    @SerializedName("portfolios")
    val objectives: List<Objective>
)