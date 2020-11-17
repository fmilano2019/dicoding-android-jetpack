package com.example.moviecatalog.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.movie.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie"
    }

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieAdapter = MovieAdapter(arrayListOf()) {
            Intent(requireActivity(), DetailMovieActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, it.id)
                startActivity(this)
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshMovies.setOnRefreshListener { movieViewModel.loadPopularMovies() }
        rvMovies.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = movieAdapter
        }
        subscribeUI()
    }

    private fun subscribeUI() {
        movieViewModel.run {
            loadPopularMovies()
            getPopularMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    movieAdapter.updateMovies(it)
                    swipeRefreshMovies.isRefreshing = false
                    pbMovies.visibility = View.GONE
                }
            })
            getErrorMessage().observe(viewLifecycleOwner, {
                if (it != null) {
                    swipeRefreshMovies.isRefreshing = false
                    pbMovies.visibility = View.GONE
                }
            })
        }
    }
}