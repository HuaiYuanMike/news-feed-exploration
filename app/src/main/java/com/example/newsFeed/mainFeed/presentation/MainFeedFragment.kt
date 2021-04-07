package com.example.newsFeed.mainFeed.presentation

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R
import com.example.newsFeed.editnote.presentation.EditNoteFragment
import com.example.newsFeed.mainFeed.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFeedFragment : Fragment() {

    private val listAdapter = MainFeedListAdapter()

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: View
    private lateinit var mainFeedViewModel: MainFeedViewModel 
    private lateinit var fab: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    private val testList = listOf(
        Note(0, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(1, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?"),
        Note(2, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?")
    )

    private val testNote = Note(123456, "title1", "author", "Do you have 5 minutes today or tomorrow to catch up and give me a quick update on what opportunities you’d see as worthwhile to run past you?")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.putString(MainActivity.ARGUMENT_KEY_TEST, arguments?.getString(MainActivity.ARGUMENT_KEY_TEST))
        Log.d("mikelog", "Do we have the TEST data? ${savedInstanceState?.getString(MainActivity.ARGUMENT_KEY_TEST, "NO")}")

        mainFeedViewModel = ViewModelProvider(
            this,
            MainFeedViewModelFactory()
        ).get(MainFeedViewModel::class.java)

        mainFeedViewModel.states.observe(this, Observer { state ->
            if (state.isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }

            renderList(state.notes)
        })

        // TODO Test to insert a default note, remove when done.
//        mainFeedViewModel.dispatchAction(Action.InsertNote(testNote))
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
            EditNoteFragment()
                .show(requireFragmentManager(), EditNoteFragment::class.simpleName)
        }

        progressBar = view.findViewById(R.id.progress_circular)

        initRecyclerView(view)
    }

    override fun onResume() {
        super.onResume()

        // Invoke action
        mainFeedViewModel.dispatchAction(Action.GetAllNotes)
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
                mainFeedViewModel.dispatchAction(Action.DeleteNote(note))
            }
        }).attachToRecyclerView(recyclerView)
        //        recyclerView.addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
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