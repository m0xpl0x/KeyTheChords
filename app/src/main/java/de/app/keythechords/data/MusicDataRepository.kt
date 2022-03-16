package de.app.keythechords.data

import androidx.lifecycle.LiveData

class MusicDataRepository private constructor(private val musicDataDao : MusicDataDao) {

    fun getMusicalNotes() = musicDataDao.getMusicalNotes()
    fun getMusicalChordModes() = musicDataDao.getMusicalChordModes()
    fun getMajorKeys() = musicDataDao.getMajorKeys()
    fun getMinorKeys() = musicDataDao.getMinorKeys()

    companion object {
        @Volatile private var instance : MusicDataRepository? = null

        fun getInstance(musicDataDao: MusicDataDao) =
            instance ?: synchronized(this) {
                instance ?: MusicDataRepository(musicDataDao).also { instance = it}
            }
    }
}