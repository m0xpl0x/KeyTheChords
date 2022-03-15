package de.app.keythechords.data

import android.util.Log

class MusicWizard {

    var musicalNotesList = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
    var musicalChordModesList = listOf("major","minor","diminished")

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

    private var majorKeyList = arrayOf(cMajorKeyList,csMajorKeyList,dMajorKeyList,dsMajorKeyList,
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

    private var minorKeyList = arrayOf(cMinorKeyList,csMinorKeyList,dMinorKeyList,dsMinorKeyList,
        eMinorKeyList,fMinorKeyList,fsMinorKeyList,gMinorKeyList,
        gsMinorKeyList,aMinorKeyList,asMinorKeyList,bMinorKeyList)




    init {

        for (i in majorKeyList.indices) {//war mal bei beidem ...size -1

            majorKeyList[i].add(Pair(musicalNotesList[i%12],musicalChordModesList[0])) //I Major
            majorKeyList[i].add(Pair(musicalNotesList[(i+2)%12],musicalChordModesList[1])) // II Minor
            majorKeyList[i].add(Pair(musicalNotesList[(i+4)%12],musicalChordModesList[1])) // III Minor
            majorKeyList[i].add(Pair(musicalNotesList[(i+5)%12],musicalChordModesList[0])) // VI Major
            majorKeyList[i].add(Pair(musicalNotesList[(i+7)%12],musicalChordModesList[0])) // V Major
            majorKeyList[i].add(Pair(musicalNotesList[(i+9)%12],musicalChordModesList[1])) // VI Minor
            majorKeyList[i].add(Pair(musicalNotesList[(i+11)%12],musicalChordModesList[2])) // VII Diminished
        }



        for (i in minorKeyList.indices) {

            minorKeyList[i].add(Pair(musicalNotesList[i%12],musicalChordModesList[1])) //I Minor
            minorKeyList[i].add(Pair(musicalNotesList[(i+2)%12],musicalChordModesList[2])) // II Diminished
            minorKeyList[i].add(Pair(musicalNotesList[(i+3)%12],musicalChordModesList[0])) // bIII major
            minorKeyList[i].add(Pair(musicalNotesList[(i+5)%12],musicalChordModesList[1])) // VI Minor
            minorKeyList[i].add(Pair(musicalNotesList[(i+7)%12],musicalChordModesList[1])) // V Minor
            minorKeyList[i].add(Pair(musicalNotesList[(i+8)%12],musicalChordModesList[0])) // bVI Major
            minorKeyList[i].add(Pair(musicalNotesList[(i+10)%12],musicalChordModesList[0])) // bVII major
        }
    }

    fun fillInChords(chords : ArrayList<Pair<String,String>>) : ArrayList<Pair<String,String>> {
        Log.d("malte","chords.size : " + chords.size)
        if (chords.size < 7) {
            val keyString = findKeyOfChord(chords).toString()



            val keyLetter = keyString[1].toString()



            if (keyString.contains("major")) {
                majorKeyList.forEach { majorKey ->
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
                minorKeyList.forEach { minorKey ->
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
            var position = musicalNotesList.indexOf(oldkey)
            while (position + step < 0) {
                position += 12
            }
            val newKey = musicalNotesList[(position + step)%12]
            transposedChords.add(Pair(newKey,oldMode))

        }
        return transposedChords
    }


    fun findKeyOfChord(chords : ArrayList<Pair<String,String>>): Pair<String,String> {

        chords.forEach {c ->
            for (j in majorKeyList.indices) {

                //check in major chords

                if (lookForChordInKey(majorKeyList[j],c)) {
                    var flag = 0
                    for (i in 0 until chords.size) {
                        if(!lookForChordInKey(majorKeyList[j], chords[i])) {
                            flag = 1
                        }
                    }
                    if (flag == 0) {
                        return majorKeyList[j][0]
                    }
                }

                //check in minor chords

                if (lookForChordInKey(minorKeyList[j],c)) {
                    var flag = 0
                    for (i in 0 until chords.size) {
                        if(!lookForChordInKey(minorKeyList[j], chords[i])) {
                            flag = 1
                        }
                    }
                    if (flag == 0) {
                        return minorKeyList[j][0]
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
