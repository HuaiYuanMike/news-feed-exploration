package com.example.newsFeed.noteFeed.presentation.noteFeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R
import com.example.newsFeed.noteFeed.model.Note
import com.squareup.picasso.Picasso

class NoteFeedListAdapter : RecyclerView.Adapter<NoteFeedListAdapter.NoteFeedViewHolder>() {

    var itemList: List<Note> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteFeedViewHolder {
        return NoteFeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note_feed, parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int) = 0

    override fun onBindViewHolder(holder: NoteFeedViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class NoteFeedViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val content = itemView.findViewById<TextView>(R.id.content)
        private val imageDisplay = itemView.findViewById<ImageView>(R.id.image_display)

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content

            // load and display image
            if (note.imageUri.isNotEmpty()) {
                Picasso.with(itemView.context).load(note.imageUri).fit().into(imageDisplay)
            }
        }
    }

    fun updateList(newList: List<Note>) {
        DiffUtil.calculateDiff(
            NoteFeedDiffUtilCallback(
                itemList,
                newList
            )
        ).also {
            diffResult -> diffResult.dispatchUpdatesTo(this)
            itemList = newList
        }
    }

    class NoteFeedDiffUtilCallback(val oldList: List<Note>, val newList: List<Note>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    }
}