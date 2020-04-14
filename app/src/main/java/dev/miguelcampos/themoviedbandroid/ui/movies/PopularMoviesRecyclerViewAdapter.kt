package dev.miguelcampos.themoviedbandroid.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import dev.miguelcampos.themoviedbandroid.R
import dev.miguelcampos.themoviedbandroid.common.Constantes
import dev.miguelcampos.themoviedbandroid.retrofit.response.Movie
import kotlinx.android.synthetic.main.fragment_movies.view.*


class PopularMoviesRecyclerViewAdapter: RecyclerView.Adapter<PopularMoviesRecyclerViewAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvMovieTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()
        holder.ivMoviePoster.load(Constantes.IMAGE_BASE_URL+item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_cine)
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }

        if(position == movies.size-1 || position == movies.size-2) {
            holder.mView.setPadding(0,0,0,180)
        }
    }

    fun setData(newMovies: List<Movie>) {
        movies = newMovies
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivMoviePoster: ImageView = mView.image_view_movie_poster
        val tvMovieTitle: TextView = mView.text_view_titulo_pelicula
        val tvRating: TextView = mView.text_view_rating
    }
}
