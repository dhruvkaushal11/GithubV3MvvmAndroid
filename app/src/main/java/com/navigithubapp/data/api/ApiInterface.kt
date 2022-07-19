package com.navigithubapp.data.api


import com.navigithubapp.data.modal.Commit
import com.navigithubapp.data.modal.GithubResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("/repos/{owner}/{repo}/pulls?state=closed")
    suspend fun getClosePillRequest(@Path("owner") owner: String, @Path("repo") repo: String,  @Query("page") page: Int): List<Commit>

}