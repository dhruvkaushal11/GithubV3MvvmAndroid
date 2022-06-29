package com.navigithubapp.data.modal

data class Commit (
    val title: String,
    val body: String,
    val closed_at : String,
    val user : User
)