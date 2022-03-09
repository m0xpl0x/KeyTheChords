package de.app.keythechords.data

class MusicWizard {

    var musicalNotesList = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
    var musicalChordModesList = listOf("major","minor","diminished")

//var musicalKeyModesList = listOf("major","minor")

//Major Chords

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

    var majorKeyList = arrayOf(cMajorKeyList,csMajorKeyList,dMajorKeyList,dsMajorKeyList,
        eMajorKeyList,fMajorKeyList,fsMajorKeyList,gMajorKeyList,
        gsMajorKeyList,aMajorKeyList,asMajorKeyList,bMajorKeyList);

//Minor Chords

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

    var minorKeyList = arrayOf(cMinorKeyList,csMinorKeyList,dMinorKeyList,dsMinorKeyList,
        eMinorKeyList,fMinorKeyList,fsMinorKeyList,gMinorKeyList,
        gsMinorKeyList,aMinorKeyList,asMinorKeyList,bMinorKeyList)




    init {

        for (i in 0 until majorKeyList.size-1) {

            majorKeyList[i].add(Pair(musicalNotesList[i%12],musicalChordModesList[0])) //I Major
            majorKeyList[i].add(Pair(musicalNotesList[(i+2)%12],musicalChordModesList[1])) // II Minor
            majorKeyList[i].add(Pair(musicalNotesList[(i+4)%12],musicalChordModesList[1])) // III Minor
            majorKeyList[i].add(Pair(musicalNotesList[(i+5)%12],musicalChordModesList[0])) // VI Major
            majorKeyList[i].add(Pair(musicalNotesList[(i+7)%12],musicalChordModesList[0])) // V Major
            majorKeyList[i].add(Pair(musicalNotesList[(i+9)%12],musicalChordModesList[1])) // VI Minor
            majorKeyList[i].add(Pair(musicalNotesList[(i+11)%12],musicalChordModesList[2])) // VII Diminished
        }



        for (i in 0 until minorKeyList.size-1) {

            minorKeyList[i].add(Pair(musicalNotesList[i%12],musicalChordModesList[1])) //I Minor
            minorKeyList[i].add(Pair(musicalNotesList[(i+2)%12],musicalChordModesList[2])) // II Diminished
            minorKeyList[i].add(Pair(musicalNotesList[(i+3)%12],musicalChordModesList[0])) // bIII major
            minorKeyList[i].add(Pair(musicalNotesList[(i+5)%12],musicalChordModesList[1])) // VI Minor
            minorKeyList[i].add(Pair(musicalNotesList[(i+7)%12],musicalChordModesList[1])) // V Minor
            minorKeyList[i].add(Pair(musicalNotesList[(i+8)%12],musicalChordModesList[0])) // bVI Major
            minorKeyList[i].add(Pair(musicalNotesList[(i+10)%12],musicalChordModesList[0])) // bVII major
        }

        var testChord0 = Pair("C","major")
        var testChord1 = Pair("F#","diminished")
        var testChord2 = Pair("G","major")
        var testChords = arrayListOf(testChord0,testChord1,testChord2)

        println(findKeyOfChord(testChords))


        if (lookForChordInKey(cMajorKeyList,testChord0)) {
            println("success");
        }

        if (!lookForChordInKey(csMajorKeyList,testChord0)) {
            println("fail");
        }

    }

    fun findKeyOfChord(chords : ArrayList<Pair<String,String>>): String {

        chords.forEach {c ->
            for (j in 0 until majorKeyList.size-1) {

                //check in major chords

                if (lookForChordInKey(majorKeyList[j],c)) {
                    var flag : Int = 0
                    for (i in 0 until chords.size -1) {
                        if(!lookForChordInKey(majorKeyList[j], chords[i])) {
                            flag = 1
                        }
                    }
                    if (flag == 0) {
                        return majorKeyList[j][0].toString()
                    }
                }

                //check in minor chords

                if (lookForChordInKey(minorKeyList[j],c)) {
                    var flag : Int = 0
                    for (i in 0 until chords.size -1) {
                        if(!lookForChordInKey(minorKeyList[j], chords[i])) {
                            flag = 1
                        }
                    }
                    if (flag == 0) {
                        return minorKeyList[j][0].toString()
                    }
                }
            }
        }

        return "404"
    }


    fun lookForChordInKey(key : ArrayList<Pair<String,String>>, chord : Pair<String,String> ) : Boolean {

        key.forEach {
            if (it.first == chord.first && it.second == chord.second) {
                return true
            }
        }
        return false
    }

}
