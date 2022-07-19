package com.navigithubapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.navigithubapp.R
import com.navigithubapp.data.modal.Commit
import com.navigithubapp.databinding.ItemGithubBinding
import com.navigithubapp.utils.UtilityMethods

class PullRequestAdapter :  PagingDataAdapter<Commit, PullRequestAdapter.MentorViewHolder>(Comparator) {
    private var data = emptyList<Commit>()

    inner class MentorViewHolder(val binding: ItemGithubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(commit: Commit) {
            binding.commit = commit
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder =
        MentorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_github, parent, false))

    fun setData(data: List<Commit>) {
        this.data = data
        notifyDataSetChanged()
    }
    object Comparator: DiffUtil.ItemCallback<Commit>() {
        override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
            // Id is unique.
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
            return oldItem == newItem

        }
    }
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.bind(data[position])

    }

}