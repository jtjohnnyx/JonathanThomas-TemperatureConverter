package com.example.jonathanthomas_temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seekbar1 = findViewById<SeekBar>(R.id.seekbar1)
        val text1 = findViewById<TextView>(R.id.text1)
        val seekbar2 = findViewById<SeekBar>(R.id.seekbar2)
        val text2 = findViewById<TextView>(R.id.text2)
        val msg = findViewById<TextView>(R.id.message)

        var sb1 = false
        var sb2 = false

        seekbar1.progress = 0
        seekbar2.progress = 32

        text1.text = "${seekbar1.progress}°C"
        text2.text = "${seekbar2.progress}°F"

        seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar1: SeekBar, progress: Int, fromUser: Boolean) {
                if (!sb2) {
                    seekbar2.progress = (1.8 * seekbar1.progress + 32).toInt()
                    text1.text = "$progress°C"
                    text2.text = "${seekbar2.progress}°F"
                }
                if (seekbar1.progress <= 20)
                    msg.text = "I wish it were warmer."
                else
                    msg.text = "I wish it were colder."
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                sb1 = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                sb1 = false
            }
        })

        seekbar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar2: SeekBar, progress: Int, fromUser: Boolean) {
                if (!sb1) {
                    seekbar1.progress = ((5.0 / 9.0) * (seekbar2.progress - 32)).toInt()
                    text1.text = "${seekbar1.progress}°C"
                    text2.text = "$progress°F"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                sb2 = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (seekbar2.progress < 32)
                    seekbar2.progress = 32
                sb2 = false
            }
        })

    }

}