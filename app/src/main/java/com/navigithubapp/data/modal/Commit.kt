package com.navigithubapp.data.modal

import com.google.gson.annotations.SerializedName

data class Commit (
    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String,

    @SerializedName("closed_at")
    val closedAt : String,

    @SerializedName("created_at")
    val createdAt : String,

    @SerializedName("user")
    val user : User
)