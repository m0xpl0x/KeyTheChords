package de.app.keythechords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.app.keythechords.data.MusicWizard
import de.app.keythechords.ui.Fragments.Adapters.ViewPagerAdapter
import de.app.keythechords.ui.Fragments.FillFragment
import de.app.keythechords.ui.Fragments.KeyFragment
import de.app.keythechords.ui.Fragments.TransposeFragment

class MainActivity : AppCompatActivity(){
    private lateinit var tranposeButton: Button;
    var selectedKeys = Array<String>(7) {it -> "----"}
    var selectedModes = Array<String>(7) {it -> "----"}

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()

        val mw = MusicWizard()
        println("succeded?")
        
        setUpSpinner()

        }

    private fun setUpSpinner() {

        val spinnerKeyList = ArrayList<Spinner>()
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
                    Toast.makeText( //remove this toast later
                        this@MainActivity,
                        "item: :  ${(position).toString()}",
                        Toast.LENGTH_SHORT
                    ).show();
                    selectedKeys[spinnerKeyList.indexOf(spinner)] = adapterview?.getItemAtPosition(position) as String
                    //Log.d("malte",getResources().getResourceEntryName(spinner.id).toString())
                    //Log.d("malte",spinnerKeyList.indexOf(spinner).toString())
                    Log.d("malte",selectedKeys[spinnerKeyList.indexOf(spinner)] + " " + spinnerKeyList.indexOf(spinner) )

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        val spinnerModeList = ArrayList<Spinner>()
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
                    selectedModes[spinnerModeList.indexOf(spinner)] = adapterview?.getItemAtPosition(position) as String
                    Log.d("malte",selectedModes[spinnerModeList.indexOf(spinner)] + " " + spinnerModeList.indexOf(spinner) )

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }






    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TransposeFragment(),"Tranpose")
        adapter.addFragment(KeyFragment(),"Key")
        adapter.addFragment(FillFragment(),"Fill")
        val viewPager : ViewPager
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        val tabs : TabLayout
        tabs = findViewById(R.id.tabLayout)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_transpose)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_key)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_fill)
    }


}
