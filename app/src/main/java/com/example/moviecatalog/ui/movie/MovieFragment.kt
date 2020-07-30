package com.example.moviecatalog.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.movie.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_movie.*

class MoviesFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        rvMovie.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = MovieAdapter(
                movieViewModel.getMovieData()
            ) {
                Intent(requireActivity(), DetailMovieActivity::class.java).apply {
                    putExtra(EXTRA_MOVIE, it.id)
                    startActivity(this)
                }
            }
        }
    }

    private fun init() {
        movieViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
                MovieViewModel::class.java
            ).apply {
                loadMovieData(requireContext())
            }
    }
}