package com.diegoferreiracaetano.basekotlin.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegoferreiracaetano.basekotlin.mock.DogMother
import com.diegoferreiracaetano.basekotlin.ui.main.MainViewModel
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import io.reactivex.Flowable
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MainViewModelTest {

    @Mock
    private lateinit var getListDogsInteractor: GetListDogsInteractor

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(getListDogsInteractor)
    }


    @Test
    @Throws(Exception::class)
    fun `Given dogs, When load gogs, Should update result`() {

        // Given

        val breeds =  listOf(DogMother.fakeDog.breed)
        val dogs =  listOf(DogMother.fakeDog)


        `when`(getListDogsInteractor.execute(any(GetListDogsInteractor.Request::class.java))).thenReturn(Flowable.just(dogs))

        // When

        viewModel.getPhotoDog(breeds)

        // Should

        assertThat(dogs, `is`(viewModel.result.value))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load dogs with error, Should update error`() {

        //Given
        val breeds =  listOf(DogMother.fakeDog.breed)
        val e = Exception()

        `when`(getListDogsInteractor.execute(any(GetListDogsInteractor.Request::class.java))).thenReturn(Flowable.error(e))

        // When

        viewModel.getPhotoDog(breeds)

        // Should

        assertThat(e, `is`(viewModel.error.value))
    }

    @Test
    @Throws(Exception::class)
    fun  `Given unknown error emission, When load dogs with unknown error, Should update error`() {

        // Given
        val breeds =  listOf(DogMother.fakeDog.breed)

        `when`(getListDogsInteractor.execute(any(GetListDogsInteractor.Request::class.java))).thenReturn(Flowable.empty())

        // When

        viewModel.getPhotoDog(breeds)

        // Should

        assertThat(null, `is`(viewModel.result.value))
    }
}
