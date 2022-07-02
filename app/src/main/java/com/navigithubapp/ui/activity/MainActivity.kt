package com.navigithubapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigithubapp.BuildConfig
import com.navigithubapp.R
import com.navigithubapp.data.modal.Commit
import com.navigithubapp.databinding.ActivityMainBinding
import com.navigithubapp.ui.adapter.PullRequestAdapter
import com.navigithubapp.ui.viewmodel.FetchPullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<FetchPullRequestViewModel>()

    private var pullRequestAdapter = PullRequestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setUpRecyclerViews()

        subscribeObservers()

        viewModel.fetchPullRequest(BuildConfig.OWNER, BuildConfig.REPO)



    }


    private fun subscribeObservers() {
        viewModel.observeListGithub().observe(this, Observer {
            it?.let { data ->
                if(data.isNotEmpty()){
                    pullRequestAdapter.setData(data)
                }
                else{
                    binding.noData.visibility = View.VISIBLE
                }

            }

        })

        viewModel.observeLoadingState().observe(this, Observer {
            it?.let { data ->
               binding.isLoading  = data
            }
        })

    }

    private fun setUpRecyclerViews() {


        binding.recyclerView.adapter = pullRequestAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)


    }

}