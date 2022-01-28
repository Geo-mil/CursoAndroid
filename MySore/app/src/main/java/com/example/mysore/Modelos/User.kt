package com.example.mysore.Modelos

data class User(
    var id: Int,
    var name: String = "",
    var surname: String = "",
    var email: String,
    var articles: List<Items>,
)
