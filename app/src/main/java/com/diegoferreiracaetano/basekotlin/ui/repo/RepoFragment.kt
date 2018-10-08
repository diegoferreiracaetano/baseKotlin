package com.diegoferreiracaetano.basekotlin.ui.repo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.basekotlin.databinding.FragmentRepoBinding
import com.diegoferreiracaetano.basekotlin.ui.repo.adapter.RepoAdapter
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.repo.Repo
import kotlinx.android.synthetic.main.fragment_repo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoFragment : Fragment(){

    companion object {
        fun newInstance() = RepoFragment()
    }

    val viewModel: RepoViewModel by viewModel()

    private lateinit var binding: FragmentRepoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_repo, container, false)
        binding.let {
            it.setLifecycleOwner(this@RepoFragment)
            it.viewModel = viewModel
        }
        return binding.root
    }

}
