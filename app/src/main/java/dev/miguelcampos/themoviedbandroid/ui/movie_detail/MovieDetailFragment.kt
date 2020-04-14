package dev.miguelcampos.themoviedbandroid.ui.movie_detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import dev.miguelcampos.themoviedbandroid.R
import dev.miguelcampos.themoviedbandroid.common.Constantes
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import kotlinx.android.synthetic.main.movie_detail_fragment.view.*

class MovieDetailFragment : Fragment() {
    private var idMovie = -1

    companion object {
        fun newInstance(idMovie: Int?)= MovieDetailFragment().apply {
            arguments = Bundle().apply {
                idMovie?.let { putInt(Constantes.ARG_ID_MOVIE, it) }
            }
        }
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt(Constantes.ARG_ID_MOVIE)?.let {
            idMovie = it
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.movie_detail_fragment, container, false)

        if(idMovie != -1) {

            viewModel.getMovie(idMovie)?.observe(viewLifecycleOwner, Observer {

                activity?.title = it.title
                text_view_budget.text = it.budget.toString()
                text_view_popularity.text = it.popularity.toString()
                text_view_rating.text = it.vote_average.toString()
                text_view_overview.text = it.overview
                image_view_movie_poster.load(Constantes.IMAGE_BASE_URL+it.poster_path){
                    crossfade(true)
                    placeholder(R.drawable.ic_cine)
                    transformations(CircleCropTransformation())
                }
            })
        }

        return v


    }


}
