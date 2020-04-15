package com.gpillaca.cleanarchitecturesample

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_location_item.view.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var items: List<Location> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_location_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(location: Location) {
            with(location) {
                itemView.locationCoordinates.text =
                    "${latitude.toPrettifiedString()} | ${longitude.toPrettifiedString()}"
                itemView.locationDate.text = date.toPrettifiedString()
            }
        }
    }
}

private fun Date.toPrettifiedString(): String =
    SimpleDateFormat.getDateTimeInstance().run {
        format(this@toPrettifiedString)
    }

private fun Double.toPrettifiedString(): String {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this)
}