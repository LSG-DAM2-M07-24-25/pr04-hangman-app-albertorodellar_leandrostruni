package com.example.hangmanapp.model

data class Letter(
    val char: Char,
    var pulsado: Boolean = false
)