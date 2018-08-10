package com.diegoferreiracaetano.basekotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.basekotlin.databinding.MainFragmentBinding
import com.diegoferreiracaetano.domain.dog.Dog
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModel()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDog()

        viewModel.result.observe(this, Observer {
            showList(it)
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(context,"Error ",Toast.LENGTH_LONG).show()
        })
    }

    private fun showList(dogs: List<Dog>) {
        rv_animal_list.layoutManager = LinearLayoutManager(activity)
        rv_animal_list.adapter = AnimalAdapter(dogs, this.context!!)
    }


}
