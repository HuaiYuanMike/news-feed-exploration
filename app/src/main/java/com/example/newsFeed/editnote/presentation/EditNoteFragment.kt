package com.example.newsFeed.editnote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsFeed.R
import com.example.newsFeed.mainFeed.data.Note

class EditNoteFragment : DialogFragment() {

    private lateinit var editNoteViewModel: EditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editNoteViewModel = ViewModelProvider(this).get(EditNoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            // TODO retrieve data and populate it into a note
            val note = Note(12345, "test title", "test author", "This is a test content of a test note.")
            editNoteViewModel.insertNote(note)
            dialog?.dismiss()
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