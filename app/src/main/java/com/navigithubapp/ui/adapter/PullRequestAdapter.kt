package com.navigithubapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.navigithubapp.R
import com.navigithubapp.data.modal.Commit
import com.navigithubapp.databinding.ItemGithubBinding

class PullRequestAdapter() : RecyclerView.Adapter<PullRequestAdapter.MentorViewHolder>() {
    private var data = emptyList<Commit>()

    inner class MentorViewHolder(val binding: ItemGithubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(commit: Commit) {
            binding.commit = commit

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder =
        MentorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_github, parent, false))

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<Commit>) {
        this.data = data
        notifyDataSetChanged()
    }
    override fun getItemCount() = data.size

}