package com.gpillaca.cleanarchitecturesample.usecases

import com.gpillaca.cleanarchitecturesample.data.LocationRepository
import com.gpillaca.cleanarchitecturesample.domain.Location

class GetLocations(private val locationRepository: LocationRepository) {
    fun invoke(): List<Location> = locationRepository.getSavedLocations()
}