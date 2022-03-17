package de.app.keythechords.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MusicDataDao {
    /*private val musicalNotesList = mutableListOf<String>()
    private val musicalChordModesList = mutableListOf<String>()
    private val majorKeysList = mutableListOf<Pair<String,String>>()
    private val minorKeysList = mutableListOf<Pair<String,String>>()*/

    private var musicalNotes = List<String>(12) {""}
    private var musicalChordModes = List<String>(3) {""}
    private lateinit var majorKeys : Array<ArrayList<Pair<String,String>>>
    private lateinit var minorKeys : Array<ArrayList<Pair<String,String>>>


    init {
        musicalNotes = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
        musicalChordModes  = listOf("major","minor","diminished")

        var cMajorKeyList = ArrayList<Pair<String,String>>()
        var csMajorKeyList = ArrayList<Pair<String,String>>()
        var dMajorKeyList = ArrayList<Pair<String,String>>()
        var dsMajorKeyList = ArrayList<Pair<String,String>>()
        var eMajorKeyList = ArrayList<Pair<String,String>>()
        var fMajorKeyList = ArrayList<Pair<String,String>>()
        var fsMajorKeyList = ArrayList<Pair<String,String>>()
        var gMajorKeyList = ArrayList<Pair<String,String>>()
        var gsMajorKeyList = ArrayList<Pair<String,String>>()
        var aMajorKeyList = ArrayList<Pair<String,String>>()
        var asMajorKeyList = ArrayList<Pair<String,String>>()
        var bMajorKeyList = ArrayList<Pair<String,String>>()

        majorKeys = arrayOf(cMajorKeyList,csMajorKeyList,dMajorKeyList,dsMajorKeyList,
            eMajorKeyList,fMajorKeyList,fsMajorKeyList,gMajorKeyList,
            gsMajorKeyList,aMajorKeyList,asMajorKeyList,bMajorKeyList)

        for (i in majorKeys.indices) {//war mal bei beidem ...size -1

            majorKeys[i].add(Pair(musicalNotes[i%12],musicalChordModes[0])) //I Major
            majorKeys[i].add(Pair(musicalNotes[(i+2)%12],musicalChordModes[1])) // II Minor
            majorKeys[i].add(Pair(musicalNotes[(i+4)%12],musicalChordModes[1])) // III Minor
            majorKeys[i].add(Pair(musicalNotes[(i+5)%12],musicalChordModes[0])) // VI Major
            majorKeys[i].add(Pair(musicalNotes[(i+7)%12],musicalChordModes[0])) // V Major
            majorKeys[i].add(Pair(musicalNotes[(i+9)%12],musicalChordModes[1])) // VI Minor
            majorKeys[i].add(Pair(musicalNotes[(i+11)%12],musicalChordModes[2])) // VII Diminished
        }



        var cMinorKeyList = ArrayList<Pair<String,String>>()
        var csMinorKeyList = ArrayList<Pair<String,String>>()
        var dMinorKeyList = ArrayList<Pair<String,String>>()
        var dsMinorKeyList = ArrayList<Pair<String,String>>()
        var eMinorKeyList = ArrayList<Pair<String,String>>()
        var fMinorKeyList = ArrayList<Pair<String,String>>()
        var fsMinorKeyList = ArrayList<Pair<String,String>>()
        var gMinorKeyList = ArrayList<Pair<String,String>>()
        var gsMinorKeyList = ArrayList<Pair<String,String>>()
        var aMinorKeyList = ArrayList<Pair<String,String>>()
        var asMinorKeyList = ArrayList<Pair<String,String>>()
        var bMinorKeyList = ArrayList<Pair<String,String>>()

         minorKeys = arrayOf(cMinorKeyList,csMinorKeyList,dMinorKeyList,dsMinorKeyList,
            eMinorKeyList,fMinorKeyList,fsMinorKeyList,gMinorKeyList,
            gsMinorKeyList,aMinorKeyList,asMinorKeyList,bMinorKeyList)

        for (i in minorKeys.indices) {

            minorKeys[i].add(Pair(musicalNotes[i%12],musicalChordModes[1])) //I Minor
            minorKeys[i].add(Pair(musicalNotes[(i+2)%12],musicalChordModes[2])) // II Diminished
            minorKeys[i].add(Pair(musicalNotes[(i+3)%12],musicalChordModes[0])) // bIII major
            minorKeys[i].add(Pair(musicalNotes[(i+5)%12],musicalChordModes[1])) // VI Minor
            minorKeys[i].add(Pair(musicalNotes[(i+7)%12],musicalChordModes[1])) // V Minor
            minorKeys[i].add(Pair(musicalNotes[(i+8)%12],musicalChordModes[0])) // bVI Major
            minorKeys[i].add(Pair(musicalNotes[(i+10)%12],musicalChordModes[0])) // bVII major
        }

    }

    fun getMusicalNotes() = musicalNotes
    fun getMusicalChordModes() = musicalChordModes
    fun getMajorKeys() = majorKeys
    fun getMinorKeys() = minorKeys
}