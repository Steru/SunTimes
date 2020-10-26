package com.steru.suntime.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.steru.suntime.R
import com.steru.suntime.data.model.FormattedSunData
import com.steru.suntime.data.model.SunData
import com.steru.suntime.ui.utils.Resource
import com.steru.suntime.ui.utils.ResourceStatus.*
import com.steru.suntime.ui.utils.TimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [FormattedSunData].
 */
class SunTimeListAdapter(
    private val values: MutableList<Resource<SunData>>,
    private val lifecycleOwner: LifecycleOwner,
    private val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<SunTimeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sun_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resource = values[position]

        when (resource.status) {
            SUCCESS -> setViewValues(resource.data, holder)
            ERROR -> setErrorState(resource.message, holder)
            LOADING -> setLoadingState(holder)
        }
    }

    private fun setLoadingState(holder: ViewHolder) {
        holder.spinner.visibility = View.VISIBLE
    }

    private fun setViewValues(data: SunData?, holder: ViewHolder) {
        holder.spinner.visibility = View.GONE
        data?.apply {
            val formatter = TimeFormatter()

            // every value contains date
            holder.dateText.text = formatter.formatFullDateTimeToDate(sunrise)
            holder.sunriseText.text = formatter.formatTo24hLocalTime(sunrise)
            holder.sunsetText.text = formatter.formatTo24hLocalTime(sunset)
        }
    }

    private fun setErrorState(message: String?, holder: ViewHolder) {
        holder.spinner.visibility = View.GONE
        // todo extract to string resource
        holder.errorText.text = "Error! $message"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateText: TextView = view.findViewById(R.id.date_text)
        val sunriseText: TextView = view.findViewById(R.id.sunrise_text)
        val sunsetText: TextView = view.findViewById(R.id.sunset_text)

        val errorText: TextView = view.findViewById(R.id.error_text)
        val spinner: ProgressBar = view.findViewById(R.id.sun_item_spinner)

        override fun toString(): String {
            return super.toString() + " '" + sunsetText.text + "'"
        }
    }
}