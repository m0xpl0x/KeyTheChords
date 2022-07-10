package de.app.keythechords.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MusicDataViewModelFactory(private val musicDataRepository: MusicDataRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MusicDataViewModel(musicDataRepository) as T
    }
}