package com.example.newsFeed.mainFeed.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R
import com.example.newsFeed.mainFeed.data.Note

class MainFeedListAdapter : RecyclerView.Adapter<MainFeedListAdapter.MainFeedViewHolder>() {

    var itemList: List<Note> = arrayListOf()

    class MainFeedViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val content = itemView.findViewById<TextView>(R.id.content)

        fun bind(item: Note) {
            title.text = item.title
            content.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFeedViewHolder {
        return MainFeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_feed_note, parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int) = 0

    override fun onBindViewHolder(holder: MainFeedViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateList(newList: List<Note>) {
        DiffUtil.calculateDiff(MainFeedDiffUtilCallback(itemList, newList)).also {
            diffResult -> diffResult.dispatchUpdatesTo(this)
            itemList = newList
        }
    }

    class MainFeedDiffUtilCallback(val oldList: List<Note>, val newList: List<Note>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    }
}