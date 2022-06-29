package com.navigithubapp.data.api


import com.navigithubapp.data.modal.Commit
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/repos/{owner}/{repo}/pulls?state=closed")
    fun getClosePillRequest(@Path("owner") owner: String, @Path("repo") repo: String): Call<List<Commit>>

}