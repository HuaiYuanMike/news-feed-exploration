package com.example.newsFeed.editnote.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.newsFeed.R
import com.example.newsFeed.mainFeed.model.Note

class EditNoteFragment : DialogFragment() {

    private val editNoteViewModel: EditNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConfirmButton(view)
        editNoteViewModel.editNoteStates.observe(this,
            Observer<EditNoteState> {
                when (it) {
                    EditNoteState.Finished -> {
                        Log.d(this.javaClass.simpleName, "Note created with id: $id")
                        dialog?.dismiss()
                    }
                }
            })

    }

    private fun setupConfirmButton(view: View) {
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            val id = System.currentTimeMillis().toInt()
            val title = view.findViewById<EditText>(R.id.title_text).text.toString()
            val author = view.findViewById<EditText>(R.id.author_text).text.toString()
            val content = view.findViewById<EditText>(R.id.content_text).text.toString()

            val note = Note(id, title, author, content)
            editNoteViewModel.insertNote(note)
        }
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}
