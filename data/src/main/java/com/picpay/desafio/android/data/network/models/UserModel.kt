package com.picpay.desafio.android.data.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("img") val imgUrl: String? = null,
    @SerializedName("username") val username: String,
) : Parcelable