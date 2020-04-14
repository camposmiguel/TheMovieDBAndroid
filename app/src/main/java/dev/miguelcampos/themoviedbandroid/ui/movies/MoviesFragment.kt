package dev.miguelcampos.themoviedbandroid.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import dev.miguelcampos.themoviedbandroid.R
import dev.miguelcampos.themoviedbandroid.common.Constantes
import dev.miguelcampos.themoviedbandroid.common.MyApp
import dev.miguelcampos.themoviedbandroid.retrofit.response.Movie
import dev.miguelcampos.themoviedbandroid.ui.movie_detail.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_movies_list_land.view.*

class MoviesFragment : Fragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: PopularMoviesRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()
    private var isTablet: Boolean = false

    private var columnCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var view: View
        val isTablet = MyApp.instance.resources.getBoolean(R.bool.isTablet)

        view = when {
            isTablet -> inflater.inflate(R.layout.fragment_movies_list_land, container, false)
            else -> inflater.inflate(R.layout.fragment_movies_list, container,false)
        }

        // ViewModel
        moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        // Set the adapter
        moviesAdapter = PopularMoviesRecyclerViewAdapter(moviesViewModel, isTablet, viewLifecycleOwner)

        with(view.list) {
            columnCount = when {
                isTablet -> 1
                else -> 2
            }

            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = moviesAdapter
        }

        // Observer Popular Movies
        moviesViewModel.getPopularMovies()?.observe(viewLifecycleOwner, Observer {
            popularMovies = it
            when { isTablet -> moviesViewModel.selectedMovie.setValue(popularMovies.get(0).id) }
            moviesAdapter.setData(popularMovies)
        })

        // Observer Movie Detail
        moviesViewModel.selectedMovie?.observe(viewLifecycleOwner, Observer {
            when {
                isTablet -> {
                    val navHostFragment =
                        childFragmentManager.findFragmentById(R.id.movies_nav_container) as NavHostFragment
                    val bundle = Bundle()
                    bundle.putInt(Constantes.ARG_ID_MOVIE, it)
                    navHostFragment.navController.navigate(R.id.fragment_movie_detail, bundle)
                } else -> {
                    activity?.let{ it2 ->
                        val intent = Intent (it2, MovieDetailActivity::class.java)
                        intent.putExtra(Constantes.ARG_ID_MOVIE, it)
                        startActivity(intent)
                    }
                }
            }
        })

        return view
    }

}
