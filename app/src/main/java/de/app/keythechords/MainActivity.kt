package de.app.keythechords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.app.keythechords.data.MusicWizard
import de.app.keythechords.ui.Fragments.Adapters.ViewPagerAdapter
import de.app.keythechords.ui.Fragments.FillFragment
import de.app.keythechords.ui.Fragments.KeyFragment
import de.app.keythechords.ui.Fragments.TransposeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var tranposeButton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()

        val mw = MusicWizard()
        println("succeded?")

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
