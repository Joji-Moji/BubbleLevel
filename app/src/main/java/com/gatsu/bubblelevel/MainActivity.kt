package com.gatsu.bubblelevel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gatsu.bubblelevel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var sManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val sListener = object : SensorEventListener{
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val sData = "X:${value?.get(0)}\nY:${value?.get(1)}\nZ:${value?.get(2)}"
                binding.text.text = sData
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

        }

        sManager.registerListener(sListener, sensor,SensorManager.SENSOR_DELAY_NORMAL)

    }
}