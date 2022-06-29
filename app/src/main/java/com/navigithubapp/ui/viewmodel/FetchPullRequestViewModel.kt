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
    private val progressbarObservable =  MutableLiveData<Boolean>()
    private val pullRequestToFetch = MutableLiveData<List<Commit>>()
    fun observeListGithub(): LiveData<List<Commit>> = pullRequestToFetch
    fun observeLoadingState(): LiveData<Boolean> = progressbarObservable

    init {
        val apiInterface = ApiClient.getApiInterface(application)
        netRepo = GithubRepository.getInstance(apiInterface)

    }

    val fetchDataCallBack = object : GithubRepository.NetRepositoryCallback<List<Commit>> {
        override fun onSuccess(data: List<Commit>) {
            pullRequestToFetch.postValue(data)
            progressbarObservable.value = false
        }

        override fun onError(code: Int?, message: String?) {

            pullRequestToFetch.postValue(arrayListOf())
            progressbarObservable.value = false


        }
    }

    fun fetchPullRequest(owner : String, repo :String): LiveData<List<Commit>> {
        progressbarObservable.value = true
        netRepo.getClosedPullRequest(owner,repo, fetchDataCallBack)
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