package de.app.keythechords.data

import android.util.Log
import androidx.lifecycle.ViewModel

class MusicDataViewModel(private val musicDataRepository: MusicDataRepository) : ViewModel() {

    private var musicalNotes = List<String>(12) {""}
    private var musicalChordModes = List<String>(3) {""}
    private lateinit var majorKeys : Array<ArrayList<Pair<String,String>>>
    private lateinit var minorKeys : Array<ArrayList<Pair<String,String>>>

    fun fetchMusicalData() {
        musicalNotes = musicDataRepository.getMusicalNotes()
        musicalChordModes = musicDataRepository.getMusicalChordModes()
        majorKeys = musicDataRepository.getMajorKeys()
        minorKeys = musicDataRepository.getMinorKeys()
    }

    fun findKeyOfChord(chords : ArrayList<Pair<String,String>>): Pair<String,String> {

        chords.forEach {c ->
            for (j in majorKeys.indices) {

                //check in major chords

                if (lookForChordInKey(majorKeys[j],c)) {
                    var flag = 0
                    for (i in 0 until chords.size) {
                        if(!lookForChordInKey(majorKeys[j], chords[i])) {
                            flag = 1
                        }
                    }
                    if (flag == 0) {
                        return majorKeys[j][0]
                    }
                }

                //check in minor chords

                if (lookForChordInKey(minorKeys[j],c)) {
                    var flag = 0
                    for (i in 0 until chords.size) {
                        if(!lookForChordInKey(minorKeys[j], chords[i])) {
                            flag = 1
                        }
                    }
                    if (flag == 0) {
                        return minorKeys[j][0]
                    }
                }
            }
        }

        return Pair("Nothing Found","")
    }

    fun transposeKey(chords : ArrayList<Pair<String,String>>,step : Int) : ArrayList<Pair<String,String>> {

        val transposedChords = ArrayList<Pair<String,String>>()

        chords.forEach { chord ->
            val oldkey = chord.first
            val oldMode = chord.second
            var position = musicalNotes.indexOf(oldkey)
            while (position + step < 0) {
                position += 12
            }
            val newKey = musicalNotes[(position + step)%12]
            transposedChords.add(Pair(newKey,oldMode))

        }
        return transposedChords
    }

    fun fillInChords(chords : ArrayList<Pair<String,String>>) : ArrayList<Pair<String,String>> {
        Log.d("malte","chords.size : " + chords.size)
        if (chords.size < 7) {
            val keyString = findKeyOfChord(chords).toString()



            val keyLetter = keyString[1].toString()



            if (keyString.contains("major")) {
                majorKeys.forEach { majorKey ->
                    val currentKey = majorKey[0]
                    if (currentKey.first == keyLetter)
                    {
                        majorKey.forEach { majorChord ->
                            if (!chords.contains(majorChord)) {
                                chords.add(majorChord)
                            }
                        }
                    }
                }
            }

            if (keyString.contains("minor")) {
                minorKeys.forEach { minorKey ->
                    val currentKey = minorKey[0]
                    if (currentKey.first == keyLetter)
                    {
                        minorKey.forEach { minorChord ->
                            if (!chords.contains(minorChord)) {
                                chords.add(minorChord)
                            }
                        }
                    }
                }
            }
        }

        return chords
    }

    private fun lookForChordInKey(key : ArrayList<Pair<String,String>>, chord : Pair<String,String> ) : Boolean {

        key.forEach {
            if (it.first == chord.first && it.second == chord.second) {
                return true
            }
        }
        return false
    }

    fun getMusicalNotePosition(key : String) = musicalNotes.indexOf(key)
    fun getMusicalChordModePosition(key : String) = musicalChordModes.indexOf(key)
}