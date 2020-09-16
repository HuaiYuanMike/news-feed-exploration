package com.example.newsFeed.mainFeed.presentation

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R
import com.example.newsFeed.mainFeed.data.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainFeedFragment : Fragment() {

    private val listAdapter = MainFeedListAdapter()

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: View
    private lateinit var mainFeedViewModel: MainFeedViewModel
    private lateinit var fab: FloatingActionButton

    private val testList = listOf(
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

    private val testNote = Note(0, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFeedViewModel = ViewModelProvider(
            this,
            MainFeedViewModelFactory()
        ).get(MainFeedViewModel::class.java)

        // Observe and render states
        mainFeedViewModel.notes.observe(this,
            Observer<List<Note>> { t -> renderList(t) })

        // TODO TEST
        if (mainFeedViewModel.notes.value.isNullOrEmpty()) {
            mainFeedViewModel.insertNote(testNote)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyView = view.findViewById(R.id.empty_view)
        fab = view.findViewById(R.id.fab)

        fab.setOnClickListener {
            // TODO Open a dialog / fragment for user to add a new note

            val id = Random.nextInt(2, 200)
            mainFeedViewModel.insertNote(Note(id, "TestNode ${id.toString()}", "Mike","This is a test note content.", System.currentTimeMillis()))
        }

        initToolbar(view)

        initRecyclerView(view)
    }

    override fun onResume() {
        super.onResume()

        // Invoke action
        mainFeedViewModel.getAllNotes()
    }

    private fun renderList(itemList: List<Note>?) {

        listAdapter.updateList(itemList ?: emptyList())

        if (listAdapter.itemList.isNotEmpty()) {
            emptyView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        } else {
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun initRecyclerView(rootView: View) {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        ItemTouchHelper(object : SwipeItemTouchHelperCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val apdapter = (recyclerView.adapter as MainFeedListAdapter)
                val note = apdapter.itemList[viewHolder.adapterPosition]
                mainFeedViewModel.deleteNote(note)
            }
        }).attachToRecyclerView(recyclerView)
        //        recyclerView.addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
    }

    private fun initToolbar(rootView: View) {
        (activity as AppCompatActivity).setSupportActionBar(rootView.findViewById(R.id.tool_bar))
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    abstract class SwipeItemTouchHelperCallback : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeFlag(ACTION_STATE_SWIPE, LEFT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = false

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            c.drawRect(Rect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom),
                Paint().apply { colorFilter = PorterDuffColorFilter(recyclerView.resources.getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_OVER)})

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}