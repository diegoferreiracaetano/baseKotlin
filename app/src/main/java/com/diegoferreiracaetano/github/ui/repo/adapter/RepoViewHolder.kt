package com.diegoferreiracaetano.github.ui.repo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.github.databinding.ItemRepoBinding
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.domain.repo.Repo

class RepoViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(repo: Repo?) {
        binding.repo = repo
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemRepoBinding = DataBindingUtil.inflate(inflater, R.layout.item_repo, parent, false)
            return RepoViewHolder(binding)
        }
    }
}