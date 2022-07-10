package de.app.keythechords.utilities

import de.app.keythechords.data.Database
import de.app.keythechords.data.MusicDataRepository
import de.app.keythechords.data.MusicDataViewModelFactory

object InjectorUtils {

    fun provideMusicDataViewModelFactory() : MusicDataViewModelFactory {
        //all dependencies for the MusicDataViewModelFactory are in the following line
        val musicDataRepository = MusicDataRepository.getInstance(Database.getInstance().musicDataDao)
        return MusicDataViewModelFactory(musicDataRepository)
    }
}