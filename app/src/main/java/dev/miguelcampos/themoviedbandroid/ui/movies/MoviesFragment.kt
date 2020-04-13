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
    private lateinit var popularMovies: List<Movie>

    // TODO: Customize parameters
    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        moviesViewModel.getPopularMovies()?.observe(viewLifecycleOwner, Observer {
            popularMovies = it
        })
        return root

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = PopularMoviesRecyclerViewAdapter(popularMovies)
            }
        }
        return view
    }

}
