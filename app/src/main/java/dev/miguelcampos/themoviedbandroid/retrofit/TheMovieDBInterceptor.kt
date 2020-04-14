package dev.miguelcampos.themoviedbandroid.retrofit

import android.content.res.Resources
import dev.miguelcampos.themoviedbandroid.R
import dev.miguelcampos.themoviedbandroid.common.Constantes
import dev.miguelcampos.themoviedbandroid.common.MyApp
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor: Interceptor {

    /**
     * Intercepto toda las peticiones a la API TMDB para poder incluir:
     * api_key
     * language
     */
    override fun intercept(chain: Interceptor.Chain): Response {


        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_LANGUAGE, MyApp.instance.getString(R.string.language)
            )
            .build()

        var request = chain.request()
        request = request?.newBuilder()
            .url(urlWithParams)
            ?.addHeader("Content-Type", "application/json")
            ?.addHeader("Accept", "application/json")
            ?.build()
        return chain.proceed(request)
    }
}