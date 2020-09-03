package com.example.warrenchallenge.extensions

import java.text.NumberFormat
import java.util.*

fun Double.brazillianCurrency(): String {

    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}