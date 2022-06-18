package me.dio.simulator.domain

import com.google.gson.annotations.SerializedName

class Place(
    @SerializedName("nome")
    val name: String,
    @SerializedName("imagem")
    val image: String
)