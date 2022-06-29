package com.navigithubapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigithubapp.R
import com.navigithubapp.data.modal.Commit
import com.navigithubapp.databinding.ActivityMainBinding
import com.navigithubapp.ui.adapter.PullRequestAdapter
import com.navigithubapp.ui.viewmodel.FetchPullRequestViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FetchPullRequestViewModel
    private var pullRequestAdapter = PullRequestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = FetchPullRequestViewModel.factory(this)


        setUpRecyclerViews()

        subscribeObservers()

        viewModel.fetchPullRequest()



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