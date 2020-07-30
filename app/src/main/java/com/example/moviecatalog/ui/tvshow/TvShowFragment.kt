package com.example.moviecatalog.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.tvshow.DetailTvShowActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowsFragment : Fragment() {

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        rvTvShow.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = TvShowAdapter(
                tvShowViewModel.getTvShowData()
            ) {
                Intent(requireActivity(), DetailTvShowActivity::class.java).apply {
                    putExtra(EXTRA_TVSHOW, it.id)
                    startActivity(this)
                }
            }
        }
    }

    private fun init() {
        tvShowViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
                TvShowViewModel::class.java
            ).apply {
                loadTvShowData(requireContext())
            }
    }
}