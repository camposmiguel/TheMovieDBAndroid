package dev.miguelcampos.themoviedbandroid.ui.movies
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.miguelcampos.themoviedbandroid.repository.TheMovieDBRepository
import dev.miguelcampos.themoviedbandroid.retrofit.response.Movie


class MoviesViewModel : ViewModel() {
    private var theMovieDBRepository: TheMovieDBRepository
    private var popularMovies: LiveData<List<Movie>>

    init {
        theMovieDBRepository = TheMovieDBRepository()
        popularMovies = theMovieDBRepository.popularMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>? {
        return popularMovies
    }
}