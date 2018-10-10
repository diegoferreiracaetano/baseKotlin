package com.diegoferreiracaetano.github.ui.pull


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.diegoferreiracaetano.domain.pull.Pull

import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentPullBinding
import com.diegoferreiracaetano.github.databinding.FragmentRepoBinding
import com.diegoferreiracaetano.github.ui.pull.adapter.PullViewHolder
import kotlinx.android.synthetic.main.fragment_pull.*
import kotlinx.android.synthetic.main.item_network_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullFragment : Fragment(),PullViewHolder.OnItemClickListener {
    val viewModel: PullViewModel by viewModel()

    private lateinit var binding: FragmentPullBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pull, container, false)
        binding.setLifecycleOwner(this@PullFragment)
        binding.viewModel = viewModel
        binding.callback = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val repoName = arguments!!.getString(EXTRA_REPO_NAME)
            val ownerName = arguments!!.getString(EXTRA_OWNER_NAME)
            viewModel.setParams(Pair(ownerName,repoName))
        }
    }

    override fun onItemClick(view: View, pull: Pull) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        val EXTRA_REPO_NAME = "EXTRA_REPO_NAME"
        val EXTRA_OWNER_NAME = "EXTRA_OWNER_NAME"
    }
}
