package com.navigithubapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.navigithubapp.R
import com.navigithubapp.databinding.ActivityMainBinding
import com.navigithubapp.ui.viewmodel.FetchPullRequestViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FetchPullRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = FetchPullRequestViewModel.factory(this)
        subscribeObservers()

        viewModel.fetchPullRequest()

    }


    private fun subscribeObservers() {
        viewModel.observeListGithub().observe(this, Observer {
            it?.let { data ->

                Log.d("Receiving Data:", "" + data)

            }
        })

    }


}