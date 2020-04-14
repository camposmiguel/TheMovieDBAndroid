package dev.miguelcampos.themoviedbandroid.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.miguelcampos.themoviedbandroid.R
import dev.miguelcampos.themoviedbandroid.retrofit.response.Movie

class MoviesFragment : Fragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: PopularMoviesRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()

    private var columnCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        // Set the adapter
        moviesAdapter = PopularMoviesRecyclerViewAdapter()
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = moviesAdapter
            }
        }

        moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        moviesViewModel.getPopularMovies()?.observe(viewLifecycleOwner, Observer {
            popularMovies = it
            moviesAdapter.setData(popularMovies)
        })

        return view
    }

}
