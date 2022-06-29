package com.navigithubapp.ui.viewmodel

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.navigithubapp.data.api.ApiClient
import com.navigithubapp.data.modal.Commit
import com.navigithubapp.data.repository.GithubRepository

class FetchPullRequestViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val netRepo: GithubRepository

    private val pullRequestToFetch = MutableLiveData<List<Commit>>()
    fun observeListGithub(): LiveData<List<Commit>> = pullRequestToFetch

    init {
        val apiInterface = ApiClient.getApiInterface(application)
        netRepo = GithubRepository.getInstance(apiInterface)
    }

    val fetchDataCallBack = object : GithubRepository.NetRepositoryCallback<List<Commit>> {
        override fun onSuccess(data: List<Commit>) {
            pullRequestToFetch.postValue(data)
        }

        override fun onError(code: Int?, message: String?) {

            pullRequestToFetch.postValue(null)

        }
    }

    fun fetchPullRequest(): LiveData<List<Commit>> {

        netRepo.getClosedPullRequest(0, fetchDataCallBack)
        return pullRequestToFetch;
    }

    companion object {
        private class ViewModelFactory(
            private val application: Application
        ) : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FetchPullRequestViewModel(application) as T
            }
        }

        fun factory(activity: FragmentActivity): FetchPullRequestViewModel {
            return ViewModelProviders.of(
                activity,
                ViewModelFactory(activity.application)
            ).get(FetchPullRequestViewModel::class.java)
        }
    }
}