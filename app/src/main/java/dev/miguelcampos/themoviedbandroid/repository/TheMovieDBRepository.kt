package dev.miguelcampos.themoviedbandroid.repository

import android.widget.Toast

import androidx.lifecycle.MutableLiveData

import dev.miguelcampos.themoviedbandroid.common.Constantes
import dev.miguelcampos.themoviedbandroid.common.MyApp
import dev.miguelcampos.themoviedbandroid.retrofit.TheMovieDBClient
import dev.miguelcampos.themoviedbandroid.retrofit.TheMovieDBService
import dev.miguelcampos.themoviedbandroid.retrofit.response.Movie
import dev.miguelcampos.themoviedbandroid.retrofit.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TheMovieDBRepository {
    var theMovieDBService: TheMovieDBService? = null
    var theMovieDBClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        theMovieDBClient = TheMovieDBClient.instance
        theMovieDBService = theMovieDBClient?.getTheMovieDBService()
        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies == null) {
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMoviesResponse> = theMovieDBService!!.getPopularMovies()
        call.enqueue(object : Callback<PopularMoviesResponse> {
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful()) {
                    popularMovies!!.setValue(response.body()?.results)
                } else {
                    Toast.makeText(MyApp.instance, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la conexión", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return popularMovies
    }
}