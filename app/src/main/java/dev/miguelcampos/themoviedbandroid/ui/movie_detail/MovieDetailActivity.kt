package dev.miguelcampos.themoviedbandroid.ui.movie_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.miguelcampos.themoviedbandroid.R
import dev.miguelcampos.themoviedbandroid.common.Constantes

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val extras: Bundle? = intent.extras
        val idMovie: Int? = extras?.getInt(Constantes.ARG_ID_MOVIE)

        val f: Fragment = MovieDetailFragment.newInstance(idMovie)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.movie_detail_container, f, "")
            .commit()
    }
}
