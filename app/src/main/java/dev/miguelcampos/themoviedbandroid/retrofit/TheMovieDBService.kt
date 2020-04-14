package dev.miguelcampos.themoviedbandroid.retrofit

import dev.miguelcampos.themoviedbandroid.retrofit.response.MovieDetailResponse
import dev.miguelcampos.themoviedbandroid.retrofit.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Call<MovieDetailResponse>

}