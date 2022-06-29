package com.navigithubapp.data.api


import com.navigithubapp.data.modal.Commit
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/repos/dhruvkaushal11/GithubV3MvvmAndroid/pulls?state=closed")
    fun getClosePillRequest(): Call<List<Commit>>

}