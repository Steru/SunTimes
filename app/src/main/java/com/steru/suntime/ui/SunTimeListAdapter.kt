package com.steru.suntime.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.steru.suntime.R
import com.steru.suntime.data.model.FormattedSunData

/**
 * [RecyclerView.Adapter] that can display a [FormattedSunData].
 */
class SunTimeListAdapter(
    private val values: List<FormattedSunData>
) : RecyclerView.Adapter<SunTimeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sun_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.dateText.text = item.date
        holder.sunriseText.text = item.sunrise
        holder.sunsetText.text = item.sunset
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateText: TextView = view.findViewById(R.id.date_text)
        val sunriseText: TextView = view.findViewById(R.id.sunrise_text)
        val sunsetText: TextView = view.findViewById(R.id.sunset_text)

        override fun toString(): String {
            return super.toString() + " '" + sunsetText.text + "'"
        }
    }
}