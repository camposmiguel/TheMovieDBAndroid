package dev.miguelcampos.themoviedbandroid.ui.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.miguelcampos.themoviedbandroid.repository.TheMovieDBRepository
import dev.miguelcampos.themoviedbandroid.retrofit.response.Movie
import dev.miguelcampos.themoviedbandroid.retrofit.response.MovieDetailResponse

class MovieDetailViewModel : ViewModel() {
    private var theMovieDBRepository: TheMovieDBRepository
    private lateinit var movie: LiveData<MovieDetailResponse>

    init {
        theMovieDBRepository = TheMovieDBRepository()
    }

    fun getMovie(idMovie: Int): LiveData<MovieDetailResponse>? {
        movie = theMovieDBRepository.getMovie(idMovie)!!
        return  movie
    }
}
