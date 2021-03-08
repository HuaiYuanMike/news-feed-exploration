package com.example.newsFeed.mainFeed.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsFeed.mainFeed.model.Note
import com.example.newsFeed.mainFeed.domain.NoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFeedViewModel @Inject constructor(private val noteUseCase: NoteUseCase) : ViewModel() {

    private val TAG = javaClass.simpleName

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = _notes

    private val _states: MutableLiveData<State> = MutableLiveData(State(isLoading = false))

    val states: LiveData<State> = _states

    private val changes: Channel<Change> = Channel()

    init {
        viewModelScope.launch {
            for (change in changes) {
                // TODO remove the non-null assertion operator if possible
                _states.value = reducer(_states.value!!, change)
            }
        }
    }

    // This does not need to be suspend
    fun getAllNotes() = viewModelScope.launch(Dispatchers.Default) {
        noteUseCase.retrieveAllNotes()
            .catch { exception ->
                Log.d(TAG, exception.message)
                // TODO Error Handling
            }
            .collect { list ->
                _notes.postValue(list)
            }
    }

    fun dispatchAction(action: Action) = viewModelScope.launch {
        // Start to produce event to the stream.
        when (action) {
            is Action.InsertNote -> {
                changes.send(Change.Loading)
                // TODO Test delay to simulate long running operation, remove when done.
                delay(1000)
                // TODO change to changes.send(noteUseCase.insertNote(action.note))
                noteUseCase.insertNote(action.note)
                changes.send(Change.InsertNode)
            }
            is Action.DeleteNote -> {

            }
        }
    }

    private fun reducer(oldState: State, change: Change): State {
        return when(change) {
            Change.Loading -> oldState.copy(isLoading = change.isLoading)
            Change.InsertNode, Change.DeleteNode -> oldState.copy(isLoading = change.isLoading)
            is Change.Error -> oldState.copy(isLoading = false, error = change.error)
        }
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteUseCase.deleteNote(note)
    }

    sealed class Action {
        data class InsertNote(val note: Note) : Action()
        data class DeleteNote(val note: Note) : Action()
    }

    data class State(val isLoading: Boolean, val error: Throwable? = null)

    sealed class Change(val isLoading: Boolean,val error: Throwable? = null) {
        object Loading : Change(isLoading = true)
        object InsertNode: Change(isLoading = false)
        object DeleteNode: Change(isLoading = false)
        class Error(error: Throwable): Change(isLoading = false)
    }
}

// TODO Make the app MVI
// TODO 1. Dispatch intent (action), 2. Push the actions into a flow into lower level, 3. Subscribe (collect) the Flow which returns Changes, 4. Reduce it to States and post.
// TODO Look into possible tools to implement item 1, 2, and 3.


// TODO Next Big features
// 1. Action buttons to insert
// 1.2 Dialog for adding new note
// 2. DiffUtil for recyclerView
// 3. Swipe to remove
// 4. Drag to change position
// 5. MVI
// 6. Bitrise Test
// 67. Bitrise Code coverage

// TODO List to confirm
// 1. Kotlin coroutine launchers - launch(), async() - and withContext()
//      Kotlin running multiple coroutines concurrently
// 1.1 Kotlin CoroutineScope - ViewModel scope (V)
// 1.2 Kotlin coroutineScope suspend function - coordinate the child  (v)
// 1.3 Kotlin Coroutine Channels
// 2. ViewModel Android fragment ktx byViewModels() method (v)
// 3. Dependency injection or something else which remove the usage of ViewModelProvider and Factory
// 4. ViewModel SavedStateHandle
// 5. Paging
// 5. HILT
// 6. Retrofit - REST API - HTTP ?
// 7. Image loading library


// TODO Future
//  - Paging Library from Jetpack
// https://proandroiddev.com/exploring-paging-library-from-jetpack-c661c7399662