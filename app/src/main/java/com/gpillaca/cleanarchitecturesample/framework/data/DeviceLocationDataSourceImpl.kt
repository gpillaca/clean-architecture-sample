package com.gpillaca.cleanarchitecturesample.framework.data

import com.gpillaca.cleanarchitecturesample.data.DeviceLocationDataSource
import com.gpillaca.cleanarchitecturesample.domain.Location
import java.util.*
import kotlin.random.Random

class DeviceLocationDataSourceImpl: DeviceLocationDataSource {
    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation() =
        Location(
            random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180,
            Date()
        )
}