package com.gpillaca.cleanarchitecturesample

interface MainContract {
    interface View {
        fun updateItems(locations: List<Location>)
    }

    interface Presenter {
        fun loadData()
        fun addLocation()
    }
}