package de.app.keythechords.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MusicDataDao {
    private val musicalNotesList = mutableListOf<String>()
    private val musicalChordModesList = mutableListOf<String>()
    private val majorKeysList = mutableListOf<Pair<String,String>>()
    private val minorKeysList = mutableListOf<Pair<String,String>>()

    private val musicalNotes = MutableLiveData<List<String>>()
    private val musicalChordModes = MutableLiveData<List<String>>()
    private val majorKeys = MutableLiveData<List<Pair<String,String>>>()
    private val minorKeys = MutableLiveData<List<Pair<String,String>>>()

    init {
        musicalNotes.value = musicalNotesList
        musicalChordModes.value = musicalChordModesList
        majorKeys.value = majorKeysList
        minorKeys.value = minorKeysList
    }

    fun getMusicalNotes() = musicalNotes as LiveData<List<String>>
    fun getMusicalChordModes() = musicalChordModes as LiveData<List<String>>
    fun getMajorKeys() = majorKeys as LiveData<List<Pair<String,String>>>
    fun getMinorKeys() = minorKeys as LiveData<List<Pair<String,String>>>
}