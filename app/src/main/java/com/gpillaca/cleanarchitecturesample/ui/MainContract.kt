package com.gpillaca.cleanarchitecturesample.ui

import com.gpillaca.cleanarchitecturesample.domain.Location

interface MainContract {
    interface View {
        fun updateItems(locations: List<Location>)
    }

    interface Presenter {
        fun loadData()
        fun addLocation()
    }
}