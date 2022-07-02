package com.navigithubapp.data.repository

import android.app.Application
import android.util.Log
import com.navigithubapp.R
import com.navigithubapp.data.api.ApiInterface
import com.navigithubapp.data.modal.Commit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "GithubRepository"
@Singleton
class GithubRepository @Inject constructor(private val apiInterface: ApiInterface) {
    //Repository for Network Request
    fun getClosedPullRequest(owner: String, repo : String, callback: NetRepositoryCallback<List<Commit>>) {

        apiInterface.getClosePillRequest(owner, repo).enqueue(object : retrofit2.Callback<List<Commit>> {
            override fun onResponse(call: Call<List<Commit>>, response: Response<List<Commit>>) {
                if(response.isSuccessful)
                    callback.onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<List<Commit>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                callback.onError(null)
            }
        })
    }
    interface NetRepositoryCallback<T> {
        fun onSuccess(data: T)
        fun onError(code: Int? = null, message: String? = null)
    }

}