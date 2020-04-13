package dev.miguelcampos.themoviedbandroid.retrofit

import dev.miguelcampos.themoviedbandroid.retrofit.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>

}