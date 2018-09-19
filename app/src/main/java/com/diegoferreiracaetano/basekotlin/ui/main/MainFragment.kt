package com.diegoferreiracaetano.basekotlin.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.basekotlin.databinding.MainFragmentBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModel()

    val disposable = CompositeDisposable()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        binding.let {
            it.setLifecycleOwner(this@MainFragment)
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable.add(viewModel.getList())
        swipeLayout.setOnRefreshListener(this);
    }


    override fun onRefresh() {
        Handler().postDelayed({ swipeLayout.setRefreshing(false) }, 5000)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}
