package com.navigithubapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.navigithubapp.R
import com.navigithubapp.data.api.ApiInterface
import com.navigithubapp.data.modal.Commit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import javax.inject.Inject

import com.annchar.coinranking.data.pagingsources.GithubPagingSource
import javax.inject.Singleton

private const val TAG = "GithubRepository"

const val NETWORK_PAGE_SIZE = 500
private const val INITIAL_LOAD_SIZE = 1

@Singleton
class GithubRepository @Inject constructor(private val apiInterface: ApiInterface) {
    //Repository for Network Request
    fun getClosedPullRequest(owner: String, repo : String): LiveData<PagingData<Commit>> {


        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                GithubPagingSource(owner, repo, apiInterface)
            }
            , initialKey = 1
        ).liveData

    }
    interface NetRepositoryCallback<T> {
        fun onSuccess(data: T)
        fun onError(code: Int? = null, message: String? = null)
    }

}