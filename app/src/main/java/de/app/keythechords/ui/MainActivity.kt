package de.app.keythechords.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.app.keythechords.R
import de.app.keythechords.data.MusicDataViewModel
import de.app.keythechords.ui.fragments.adapters.ViewPagerAdapter
import de.app.keythechords.ui.fragments.FillFragment
import de.app.keythechords.ui.fragments.KeyFragment
import de.app.keythechords.ui.fragments.TransposeFragment
import de.app.keythechords.utilities.InjectorUtils

class MainActivity : AppCompatActivity() {
    var selectedKeys = Array(7) { "----" }
    var selectedModes = Array(7) { "----" }
    lateinit var spinnerKeyList: ArrayList<Spinner>
    lateinit var spinnerModeList: ArrayList<Spinner>
    private lateinit var viewModel : MusicDataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
    }

    private fun initializeUI() {
        setUpTabs()
        setUpSpinner()
        val factory = InjectorUtils.provideMusicDataViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MusicDataViewModel::class.java)
        viewModel.fetchMusicalData()

    }

    fun openSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }


    fun transpose(view: View) {
        var newChords = ArrayList<Pair<String, String>>()
        when (view.id) {
            R.id.btnTranposePlus -> {
                newChords = viewModel.transposeKey(prepareChordData(), 1)
            }
            R.id.btnTranposeMinus -> {
                newChords = viewModel.transposeKey(prepareChordData(), -1)
            }
        }
        clearSelectedKeys()
        for (i in 0 until newChords.size) {
            selectedKeys[i] = newChords[i].first

        }
        setChordData(selectedKeys, selectedModes)
        findKey(view)
    }
    @SuppressLint("SetTextI18n")
    fun findKey(view: View) {
        val key = viewModel.findKeyOfChord(prepareChordData())
        val keyText = findViewById<TextView>(R.id.tvShowKey)
        if (key.first == "Nothing Found") {
            keyText.setText(R.string.noKeyfound)
        } else
            keyText.text = getString(R.string.showKeyText) + key.first + " " + key.second
    }
    fun fill(view: View) {
        val newChords = viewModel.fillInChords(prepareChordData())
        clearSelectedKeys()
        for (i in 0 until newChords.size) {
            selectedKeys[i] = newChords[i].first
            selectedModes[i] = newChords[i].second

        }
        setChordData(selectedKeys, selectedModes)
        findKey(view)
    }

    private fun clearSelectedKeys() {
        for (i in selectedKeys.indices) {
            selectedKeys[i] = "----"
        }
    }

    private fun setChordData(keys: Array<String>, modes: Array<String>) {
        for (i in 0 until spinnerKeyList.size) {
            val position = viewModel.getMusicalNotePosition(keys[i]) + 1
            spinnerKeyList[i].setSelection(position)
        }

        for (i in 0 until spinnerModeList.size) {
            val position = viewModel.getMusicalChordModePosition(modes[i]) + 1
            spinnerModeList[i].setSelection(position)
        }
    }

    private fun prepareChordData(): ArrayList<Pair<String, String>> {

        val chords = ArrayList<Pair<String, String>>()
        for (index in selectedKeys.indices) {
            if (selectedKeys[index] != getString(R.string.emptyKey) && selectedModes[index] != getString(
                    R.string.emptyMode
                )
            ) {
                chords.add(Pair(selectedKeys[index], selectedModes[index]))
            }
        }
        return chords
    }

    private fun setUpSpinner() {

        spinnerKeyList = ArrayList()
        spinnerKeyList.add(findViewById(R.id.spKey1))
        spinnerKeyList.add(findViewById(R.id.spKey2))
        spinnerKeyList.add(findViewById(R.id.spKey3))
        spinnerKeyList.add(findViewById(R.id.spKey4))
        spinnerKeyList.add(findViewById(R.id.spKey5))
        spinnerKeyList.add(findViewById(R.id.spKey6))
        spinnerKeyList.add(findViewById(R.id.spKey7))

        spinnerKeyList.forEach { spinner ->
            ArrayAdapter.createFromResource(
                this,
                R.array.keys,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterview: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedKeys[spinnerKeyList.indexOf(spinner)] =
                        adapterview?.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) { }
            }
        }

        spinnerModeList = ArrayList()
        spinnerModeList.add(findViewById(R.id.spMode1))
        spinnerModeList.add(findViewById(R.id.spMode2))
        spinnerModeList.add(findViewById(R.id.spMode3))
        spinnerModeList.add(findViewById(R.id.spMode4))
        spinnerModeList.add(findViewById(R.id.spMode5))
        spinnerModeList.add(findViewById(R.id.spMode6))
        spinnerModeList.add(findViewById(R.id.spMode7))

        spinnerModeList.forEach { spinner ->
            ArrayAdapter.createFromResource(
                this,
                R.array.modes,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterview: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedModes[spinnerModeList.indexOf(spinner)] =
                        adapterview?.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TransposeFragment(), getString(R.string.tabTranspose))
        adapter.addFragment(KeyFragment(), getString(R.string.tabKey))
        adapter.addFragment(FillFragment(), getString(R.string.tabFill))
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        val tabs: TabLayout = findViewById(R.id.tabLayout)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_transpose)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_key)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_fill)
        tabs.selectTab(tabs.getTabAt(1))
    }
}
