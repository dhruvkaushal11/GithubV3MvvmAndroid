package com.annchar.coinranking.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.navigithubapp.data.api.ApiInterface
import com.navigithubapp.data.modal.Commit

class GithubPagingSource(private val owner: String, private val repo : String, private val apiService: ApiInterface): PagingSource<Int, Commit>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Commit> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getClosePillRequest(owner, repo, position)
            LoadResult.Page(data = response, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Commit>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}