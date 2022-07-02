package com.navigithubapp.ui.viewmodel

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.navigithubapp.data.modal.Commit
import androidx.hilt.lifecycle.ViewModelInject

import com.navigithubapp.data.repository.GithubRepository

class FetchPullRequestViewModel @ViewModelInject constructor(
    private val netRepo: GithubRepository,
    application: Application
) : AndroidViewModel(application) {

    private val progressbarObservable =  MutableLiveData<Boolean>()
    private val pullRequestToFetch = MutableLiveData<List<Commit>>()
    fun observeListGithub(): LiveData<List<Commit>> = pullRequestToFetch
    fun observeLoadingState(): LiveData<Boolean> = progressbarObservable


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

}