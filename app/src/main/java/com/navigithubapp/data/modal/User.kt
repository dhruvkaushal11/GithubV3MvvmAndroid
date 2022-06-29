package com.navigithubapp.data.modal

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("login")
    val username: String

)