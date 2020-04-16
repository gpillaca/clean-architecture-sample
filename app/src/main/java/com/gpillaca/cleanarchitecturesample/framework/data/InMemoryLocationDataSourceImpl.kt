package com.gpillaca.cleanarchitecturesample.framework.data

import com.gpillaca.cleanarchitecturesample.data.PersistenceLocationDataSource
import com.gpillaca.cleanarchitecturesample.domain.Location

class InMemoryLocationDataSourceImpl : PersistenceLocationDataSource {
    private var savedLocations = emptyList<Location>()

    override fun getPersistedLocations() = savedLocations

    override fun saveNewLocation(newLocation: Location) {
        savedLocations = savedLocations + newLocation
    }
}