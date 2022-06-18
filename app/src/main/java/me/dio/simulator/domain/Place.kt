package me.dio.simulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Place(
    @SerializedName("nome")
    val name: String,
    @SerializedName("imagem")
    val image: String
): Parcelable