package com.diegoferreiracaetano.github.ui.pull


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentPullBinding
import com.diegoferreiracaetano.github.databinding.FragmentRepoBinding
import kotlinx.android.synthetic.main.fragment_pull.*
import kotlinx.android.synthetic.main.item_network_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullFragment : Fragment() {

   // val viewModel: PullViewModel by viewModel()

    private lateinit var binding: FragmentPullBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pull, container, false)
        binding.setLifecycleOwner(this@PullFragment)
        //binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repoName = arguments?.getString(EXTRA_REPO)
    }
    companion object {
        val EXTRA_REPO = "EXTRA_REPO"
    }
}
