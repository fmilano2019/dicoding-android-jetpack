package com.example.moviecatalog.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import kotlinx.android.synthetic.main.item_tag.view.*

class DetailTagAdapter(private var tags: List<String>) :
    RecyclerView.Adapter<DetailTagAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        )
    }

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tag: String) {
            itemView.tvTag.text = tag
        }
    }
}
