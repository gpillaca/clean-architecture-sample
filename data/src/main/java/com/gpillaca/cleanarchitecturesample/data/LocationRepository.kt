package com.gpillaca.cleanarchitecturesample.data

import com.gpillaca.cleanarchitecturesample.domain.Location

class LocationRepository(
    private val deviceLocationDataSource: DeviceLocationDataSource,
    private val persistenceLocationDataSource: PersistenceLocationDataSource
) {

    fun requestNewsLocation(): List<Location> {
        val newLocation = deviceLocationDataSource.getDeviceLocation()
        persistenceLocationDataSource.saveNewLocation(newLocation)
        return persistenceLocationDataSource.getPersistedLocations()
    }

    fun getSavedLocations(): List<Location> {
        return persistenceLocationDataSource.getPersistedLocations()
    }
}

interface DeviceLocationDataSource {
    fun getDeviceLocation(): Location
}

interface PersistenceLocationDataSource {
    fun getPersistedLocations(): List<Location>
    fun saveNewLocation(newLocation: Location)
}