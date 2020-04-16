package com.gpillaca.cleanarchitecturesample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gpillaca.cleanarchitecturesample.databinding.ActivityMainBinding
import com.gpillaca.cleanarchitecturesample.domain.Location

class MainActivity : AppCompatActivity(),
    MainContract.View {

    private val presenter by lazy {
        MainPresenter(this)
    }

    private val locationsAdapter by lazy {
        LocationsAdapter()
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewLocations.adapter = locationsAdapter
        presenter.onCreateScope()
        presenter.loadData()

        binding.buttonNewLocation.setOnClickListener {
            presenter.addLocation()
        }
    }

    override fun updateItems(locations: List<Location>) {
        locationsAdapter.items = locations
    }

    override fun onDestroy() {
        presenter.onDestroyScope()
        super.onDestroy()
    }
}