package com.example.moviecatalog.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.data.TvShowEntity
import kotlinx.android.synthetic.main.item_tvshow.view.*

class TvShowAdapter(
    private var tvShows: ArrayList<TvShowEntity>,
    private var clickListener: (TvShowEntity) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        )
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShows[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            tvShowEntity: TvShowEntity,
            clickListener: (TvShowEntity) -> Unit
        ) {
            itemView.run {
                cvTvShow.setOnClickListener { clickListener(tvShowEntity) }
                tvTitle.text = tvShowEntity.title
                tvYear.text = tvShowEntity.year
                Glide.with(context)
                    .load(tvShowEntity.image)
                    .into(ivImage)
            }
        }
    }
}