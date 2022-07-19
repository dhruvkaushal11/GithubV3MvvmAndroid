package com.navigithubapp.data.modal

import com.google.gson.annotations.SerializedName

data class GithubResponse(val page: Int, val results: List<Commit>)
