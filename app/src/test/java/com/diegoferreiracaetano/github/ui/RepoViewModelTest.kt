package com.diegoferreiracaetano.github.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.github.mock.RepoDataSource
import com.diegoferreiracaetano.github.ui.repo.RepoViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepoViewModelTest{

    @Mock private lateinit var getRepoInteractor: GetListRepoInteractor
    @Mock private lateinit var callback: CallbackRepoInteractor

    private lateinit var viewModel: RepoViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RepoViewModel(getRepoInteractor,callback)
    }


    @Test
    @Throws(Exception::class)
    fun `Given repos, When loadR repos, Should update result`() {
           val dataSource =  RepoDataSource.RepoDataSourceFactory()


        `when`(getRepoInteractor.execute()).thenReturn(dataSource)

        

    }
}