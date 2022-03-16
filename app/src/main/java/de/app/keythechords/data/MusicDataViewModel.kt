package de.app.keythechords.data

import androidx.lifecycle.ViewModel

class MusicDataViewModel(private val musicDataRepository: MusicDataRepository) : ViewModel() {

    private var musicalNotes = Array<String>(12) {""}
    private var musicalChordModes = Array<String>(3) {""}
    private var majorKeys = Array<Pair<String,String>>(12) {Pair("","")}
    private var minorKeys = Array<Pair<String,String>>(12) {Pair("","")}

    fun fetchMusicalData() {
        //musicalNotes = musicDataRepository.getMusicalNotes()

    }

    fun findKey() {

    }

    fun transpose(){

    }

    fun fill(){

    }
}