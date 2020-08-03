package com.example.newsFeed.mainFeed.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R
import com.example.newsFeed.mainFeed.data.Note

class MainFeedListAdapter : RecyclerView.Adapter<MainFeedListAdapter.MainFeedViewHolder>() {

    // TODO Test list
    val itemList: List<Note> = listOf(
        Note(0, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(1, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(2, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(3, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(4, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(5, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(6, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(7, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(8, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(9, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(10, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?")
    )

    class MainFeedViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

        val title = itemView.findViewById<TextView>(R.id.title)
        val content = itemView.findViewById<TextView>(R.id.content)

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