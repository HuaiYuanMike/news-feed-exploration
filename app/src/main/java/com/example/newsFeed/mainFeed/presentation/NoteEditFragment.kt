package com.example.newsFeed.mainFeed.presentation

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.newsFeed.R

class NoteEditFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // TODO create dialog
        return AlertDialog.Builder(activity!!)
            .setView(R.layout.item_main_feed_note)
            .create()
    }
}