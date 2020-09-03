package com.example.warrenchallenge.model.objective

data class Portifolio(val totalIncome: Double, val objectives: List<Objective>) {
    val hasObjectives = objectives.isNotEmpty()
}