package com.gpillaca.cleanarchitecturesample.ui

import com.gpillaca.cleanarchitecturesample.data.LocationRepository
import com.gpillaca.cleanarchitecturesample.framework.data.DeviceLocationDataSourceImpl
import com.gpillaca.cleanarchitecturesample.framework.data.InMemoryLocationDataSourceImpl
import com.gpillaca.cleanarchitecturesample.domain.Location
import com.gpillaca.cleanarchitecturesample.usecases.GetLocations
import com.gpillaca.cleanarchitecturesample.usecases.RequestNewLocation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: MainContract.View?) : MainContract.Presenter, CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val locationRepository =
        LocationRepository(DeviceLocationDataSourceImpl(), InMemoryLocationDataSourceImpl())

    private val requestNewLocation = RequestNewLocation(locationRepository)
    private val getLocations = GetLocations(locationRepository)

    override fun loadData() {
        launch {
            val locations = withContext(Dispatchers.IO) {
                getLocations.invoke()
            }

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
        return requestNewLocation.invoke()
    }

    fun onCreateScope() {
        job = SupervisorJob()
    }

    fun onDestroyScope() {
        job.cancel()
    }
}