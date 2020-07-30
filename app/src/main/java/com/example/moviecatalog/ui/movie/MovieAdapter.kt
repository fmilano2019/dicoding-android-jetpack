package com.example.moviecatalog.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.data.MovieEntity
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    private var movies: ArrayList<MovieEntity>,
    private var clickListener: (MovieEntity) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            movieEntity: MovieEntity,
            clickListener: (MovieEntity) -> Unit
        ) {
            itemView.run {
                cvMovie.setOnClickListener { clickListener(movieEntity) }
                tvTitle.text = movieEntity.title
                tvYear.text = movieEntity.year
                Glide.with(context)
                    .load(movieEntity.image)
                    .into(ivImage)
            }
        }
    }
}