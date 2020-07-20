package com.example.moviecatalog.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.utils.DataDummy
import kotlinx.android.synthetic.main.fragment_movie.*

class MoviesFragment : Fragment() {
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
        rv_movie.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = MovieAdapter(
                movieViewModel.getMovieData()
            ) {
                // To detail movie
            }
        }
    }

    private fun init() {
        movieViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
                MovieViewModel::class.java
            )
        movieViewModel.loadMovieData(requireContext())
    }

}