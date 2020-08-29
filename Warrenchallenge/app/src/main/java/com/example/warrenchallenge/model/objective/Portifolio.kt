package com.example.warrenchallenge.model.objective

data class Portifolio(val objectives: List<Objective>) {
    val totalIncome: Double
        get() {
            var income = 0.0
            objectives.forEach {
                income += it.totalBalance
            }
            return income
        }

    val hasObjectives = objectives.isNotEmpty()
}