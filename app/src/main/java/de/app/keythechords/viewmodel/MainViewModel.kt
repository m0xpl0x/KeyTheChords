package de.app.keythechords.viewmodel

import android.view.View
import android.widget.Spinner
import android.widget.TextView
import de.app.keythechords.R
import de.app.keythechords.data.MusicWizard


import android.app.Application

import android.widget.*

import androidx.lifecycle.AndroidViewModel
import de.app.keythechords.ui.MainActivity


class MainViewModel(,
                    application: Application
) : AndroidViewModel(application) {

    var selectedKeys = Array(7) { "----"}
    var selectedModes = Array(7) {"----"}
    lateinit var spinnerKeyList : ArrayList<Spinner>
    lateinit var spinnerModeList  : ArrayList<Spinner>
    private val context = getApplication<Application>().applicationContext

    private val mw = MusicWizard()


    init {
        spinnerKeyList = MainActivity.spinnerKeyList
        spinnerModeList = MainActivity.spinnerModeList



    }

    fun transpose(view : View) {

        var newChords = ArrayList<Pair<String,String>>()
        when (view.id) {
            R.id.btnTranposePlus -> {
                newChords = mw.transposeKey(prepareChordData(),1)
            }
            R.id.btnTranposeMinus -> {
                newChords = mw.transposeKey(prepareChordData(),-1)
            }
        }

        clearSelectedKeys()

        for (i in 0 until newChords.size) {
            selectedKeys[i] = newChords[i].first

        }
        setChordData(selectedKeys,selectedModes)

        findKey(MainActivity.findViewById<TextView>(R.id.tvShowKey))

    }


    fun findKey(keyText : TextView) {
        val key = mw.findKeyOfChord(prepareChordData())
        if (key.first == "Nothing Found") {
            keyText.setText(R.string.noKeyfound)
        }
        else
            keyText.text = context.getString(R.string.showKeyText) + " " +  key.first + " " + key.second
    }

    fun fill(view : View) {
        val newChords = mw.fillInChords(prepareChordData())
        clearSelectedKeys()
        for (i in 0 until newChords.size) {
            selectedKeys[i] = newChords[i].first
            selectedModes[i] = newChords[i].second

        }
        setChordData(selectedKeys,selectedModes)
        findKey(view)
    }

    private fun clearSelectedKeys() {
        for(i in selectedKeys.indices) {
            selectedKeys[i] = "----"
        }
    }

    private fun setChordData(keys : Array<String>,modes : Array<String>) {
        for ( i in 0 until spinnerKeyList.size) {
            val position = mw.musicalNotesList.indexOf(keys[i]) + 1
            spinnerKeyList[i].setSelection(position)
        }

        for ( i in 0 until spinnerModeList.size) {
            val position = mw.musicalChordModesList.indexOf(modes[i]) + 1
            spinnerModeList[i].setSelection(position)
        }
    }

    private fun prepareChordData() : ArrayList<Pair<String,String>>{

        val chords = ArrayList<Pair<String,String>>()
        for (index in selectedKeys.indices){
            if(selectedKeys[index] != getString(R.string.emptyKey) && selectedModes[index] != getString(
                    R.string.emptyMode
                )) {
                chords.add(Pair(selectedKeys[index], selectedModes[index]))
            }
        }
        return chords
    }
}