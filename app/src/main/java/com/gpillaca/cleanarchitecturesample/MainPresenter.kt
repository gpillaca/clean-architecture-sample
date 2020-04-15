package com.gpillaca.cleanarchitecturesample

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

private val random = Random(System.currentTimeMillis())

class MainPresenter(private val view: MainContract.View?) : MainContract.Presenter, CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var savedLocations = emptyList<Location>()

    override fun loadData() {
        launch {
            val locations = withContext(Dispatchers.IO) { savedLocations }
            view?.updateItems(locations)
        }
    }

    override fun addLocation() {
        launch {
            val newLocations = withContext(Dispatchers.IO) { requestNewsLocation() }
            view?.updateItems(newLocations)
        }
    }

    private fun requestNewsLocation(): List<Location> {
        val newLocation = getDeviceLocation()
        savedLocations = savedLocations + newLocation
        return savedLocations
    }

    private fun getDeviceLocation() =
        Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())

    fun onCreateScope() {
        job = SupervisorJob()
    }

    fun onDestroyScope() {
        job.cancel()
    }
}