package com.example.newsFeed.mainFeed.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R
import com.example.newsFeed.mainFeed.data.Note

class MainFeedListAdapter : RecyclerView.Adapter<MainFeedListAdapter.MainFeedViewHolder>() {

    var itemList: List<Note> = emptyList()

    class MainFeedViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val content = itemView.findViewById<TextView>(R.id.content)

        fun bind(item: Note) {
            title.text = item.title
            content.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFeedViewHolder {
        return MainFeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_feed_card, parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int) = 0

    override fun onBindViewHolder(holder: MainFeedViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}