package de.app.keythechords.data

class MusicDataDao {

    private var musicalNotes = List(12) {""}
    private var musicalChordModes = List(3) {""}
    private var majorKeys : Array<ArrayList<Pair<String,String>>>
    private var minorKeys : Array<ArrayList<Pair<String,String>>>


    init {
        musicalNotes = listOf("C","C#","D","D#","E","F","F#","G","G#","A","A#","B")
        musicalChordModes  = listOf("major","minor","diminished")

        val cMajorKeyList = ArrayList<Pair<String,String>>()
        val csMajorKeyList = ArrayList<Pair<String,String>>()
        val dMajorKeyList = ArrayList<Pair<String,String>>()
        val dsMajorKeyList = ArrayList<Pair<String,String>>()
        val eMajorKeyList = ArrayList<Pair<String,String>>()
        val fMajorKeyList = ArrayList<Pair<String,String>>()
        val fsMajorKeyList = ArrayList<Pair<String,String>>()
        val gMajorKeyList = ArrayList<Pair<String,String>>()
        val gsMajorKeyList = ArrayList<Pair<String,String>>()
        val aMajorKeyList = ArrayList<Pair<String,String>>()
        val asMajorKeyList = ArrayList<Pair<String,String>>()
        val bMajorKeyList = ArrayList<Pair<String,String>>()

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



        val cMinorKeyList = ArrayList<Pair<String,String>>()
        val csMinorKeyList = ArrayList<Pair<String,String>>()
        val dMinorKeyList = ArrayList<Pair<String,String>>()
        val dsMinorKeyList = ArrayList<Pair<String,String>>()
        val eMinorKeyList = ArrayList<Pair<String,String>>()
        val fMinorKeyList = ArrayList<Pair<String,String>>()
        val fsMinorKeyList = ArrayList<Pair<String,String>>()
        val gMinorKeyList = ArrayList<Pair<String,String>>()
        val gsMinorKeyList = ArrayList<Pair<String,String>>()
        val aMinorKeyList = ArrayList<Pair<String,String>>()
        val asMinorKeyList = ArrayList<Pair<String,String>>()
        val bMinorKeyList = ArrayList<Pair<String,String>>()

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