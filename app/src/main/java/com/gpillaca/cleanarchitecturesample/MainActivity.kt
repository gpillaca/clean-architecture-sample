package com.gpillaca.cleanarchitecturesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gpillaca.cleanarchitecturesample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val random = Random(System.currentTimeMillis())
    private var savedLocations = emptyList<Location>()
    private val locationsAdapter = LocationsAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = SupervisorJob()

        binding.recyclerviewLocations.adapter = locationsAdapter

        launch {
            locationsAdapter.items = withContext(Dispatchers.IO) { savedLocations }
        }

         binding.buttonNewLocation.setOnClickListener {
            launch {
                val newLocations = withContext(Dispatchers.IO) { requestNewsLocation() }
                locationsAdapter.items = newLocations
            }
        }
    }

    private fun requestNewsLocation(): List<Location> {
        val newLocation = getDeviceLocation()
        savedLocations = savedLocations + newLocation
        return savedLocations
    }

    private fun getDeviceLocation() =
        Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}