package com.diegoferreiracaetano.github.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentRepoBinding
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
