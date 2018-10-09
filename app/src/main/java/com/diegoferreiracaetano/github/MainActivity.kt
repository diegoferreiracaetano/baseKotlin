package com.diegoferreiracaetano.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegoferreiracaetano.github.ui.repo.RepoFragment
import com.diegoferreiracaetano.github.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RepoFragment.newInstance())
                    .commitNow()
        }
    }

}
