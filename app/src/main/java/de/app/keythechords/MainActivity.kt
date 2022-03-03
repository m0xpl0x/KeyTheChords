package de.app.keythechords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import de.app.keythechords.data.MusicWizard

class MainActivity : AppCompatActivity() {
    private lateinit var tranposeButton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mw = MusicWizard()
        println("succeded?")

        }
    }
