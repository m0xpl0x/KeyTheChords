package de.app.keythechords.data

class Database private constructor() {

    var musicDataDao = MusicDataDao()
            private set

    companion object {
        @Volatile private var instance : Database? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Database().also { instance = it}
            }
    }
}