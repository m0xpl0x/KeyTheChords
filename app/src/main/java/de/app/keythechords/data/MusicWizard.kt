package de.app.keythechords.data

import android.util.Log

class MusicWizard {

    var musicalNotes = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
    var musicalChordModes = listOf("major","minor","diminished")

//var musicalKeyModesList = listOf("major","minor")

//Major Chords

    private var cMajorKeyList = ArrayList<Pair<String,String>>()
    private var csMajorKeyList = ArrayList<Pair<String,String>>()
    private var dMajorKeyList = ArrayList<Pair<String,String>>()
    private var dsMajorKeyList = ArrayList<Pair<String,String>>()
    private var eMajorKeyList = ArrayList<Pair<String,String>>()
    private var fMajorKeyList = ArrayList<Pair<String,String>>()
    private var fsMajorKeyList = ArrayList<Pair<String,String>>()
    private var gMajorKeyList = ArrayList<Pair<String,String>>()
    private var gsMajorKeyList = ArrayList<Pair<String,String>>()
    private var aMajorKeyList = ArrayList<Pair<String,String>>()
    private var asMajorKeyList = ArrayList<Pair<String,String>>()
    private var bMajorKeyList = ArrayList<Pair<String,String>>()

    private var majorKeys = arrayOf(cMajorKeyList,csMajorKeyList,dMajorKeyList,dsMajorKeyList,
        eMajorKeyList,fMajorKeyList,fsMajorKeyList,gMajorKeyList,
        gsMajorKeyList,aMajorKeyList,asMajorKeyList,bMajorKeyList)

//Minor Chords

    private var cMinorKeyList = ArrayList<Pair<String,String>>()
    private var csMinorKeyList = ArrayList<Pair<String,String>>()
    private var dMinorKeyList = ArrayList<Pair<String,String>>()
    private var dsMinorKeyList = ArrayList<Pair<String,String>>()
    private var eMinorKeyList = ArrayList<Pair<String,String>>()
    private var fMinorKeyList = ArrayList<Pair<String,String>>()
    private var fsMinorKeyList = ArrayList<Pair<String,String>>()
    private var gMinorKeyList = ArrayList<Pair<String,String>>()
    private var gsMinorKeyList = ArrayList<Pair<String,String>>()
    private var aMinorKeyList = ArrayList<Pair<String,String>>()
    private var asMinorKeyList = ArrayList<Pair<String,String>>()
    private var bMinorKeyList = ArrayList<Pair<String,String>>()

    private var minorKeys = arrayOf(cMinorKeyList,csMinorKeyList,dMinorKeyList,dsMinorKeyList,
        eMinorKeyList,fMinorKeyList,fsMinorKeyList,gMinorKeyList,
        gsMinorKeyList,aMinorKeyList,asMinorKeyList,bMinorKeyList)




    init {

        for (i in majorKeys.indices) {//war mal bei beidem ...size -1

            majorKeys[i].add(Pair(musicalNotes[i%12],musicalChordModes[0])) //I Major
            majorKeys[i].add(Pair(musicalNotes[(i+2)%12],musicalChordModes[1])) // II Minor
            majorKeys[i].add(Pair(musicalNotes[(i+4)%12],musicalChordModes[1])) // III Minor
            majorKeys[i].add(Pair(musicalNotes[(i+5)%12],musicalChordModes[0])) // VI Major
            majorKeys[i].add(Pair(musicalNotes[(i+7)%12],musicalChordModes[0])) // V Major
            majorKeys[i].add(Pair(musicalNotes[(i+9)%12],musicalChordModes[1])) // VI Minor
            majorKeys[i].add(Pair(musicalNotes[(i+11)%12],musicalChordModes[2])) // VII Diminished
        }



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


    private fun lookForChordInKey(key : ArrayList<Pair<String,String>>, chord : Pair<String,String> ) : Boolean {

        key.forEach {
            if (it.first == chord.first && it.second == chord.second) {
                return true
            }
        }
        return false
    }

}
