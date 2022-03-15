package de.app.keythechords.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.app.keythechords.R
import de.app.keythechords.data.MusicWizard
import de.app.keythechords.ui.Fragments.Adapters.ViewPagerAdapter
import de.app.keythechords.ui.Fragments.FillFragment
import de.app.keythechords.ui.Fragments.KeyFragment
import de.app.keythechords.ui.Fragments.TransposeFragment
import de.app.keythechords.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(){


    var spinnerKeyList = ArrayList<Spinner>()
    var spinnerModeList = ArrayList<Spinner>()
    val viewModel : MainViewModel by viewModels()

    /*

    TODO("MVVM Struktur")
    TODO("dependency injection - Koin")
    TODO("darkmode")

    */



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val string = getString(R.string.showKeyText)

        setUpTabs()


        setUpSpinner()

        }
    fun openSettingsActivity(view : View) {
        val intent =  Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }


    fun transpose(view : View) {

    }


    fun findKey(view : View) {
        viewModel.findKey(findViewById<TextView>(R.id.tvShowKey))
    }

    fun fill(view : View) {

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

        spinnerKeyList.forEach {spinner ->
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
                    viewModel.selectedKeys[spinnerKeyList.indexOf(spinner)] = adapterview?.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
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

        spinnerModeList.forEach {spinner ->
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
                    viewModel.selectedModes[spinnerModeList.indexOf(spinner)] = adapterview?.getItemAtPosition(position) as String

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TransposeFragment(),getString(R.string.tabTranspose))
        adapter.addFragment(KeyFragment(),getString(R.string.tabKey))
        adapter.addFragment(FillFragment(),getString(R.string.tabFill))
        val viewPager : ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        val tabs : TabLayout = findViewById(R.id.tabLayout)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_transpose)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_key)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_fill)
        tabs.selectTab( tabs.getTabAt(1))
    }


}
