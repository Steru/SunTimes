package com.steru.suntime.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.steru.suntime.R
import com.steru.suntime.data.model.FormattedSunData
import com.steru.suntime.data.model.SunData
import com.steru.suntime.ui.utils.Resource
import com.steru.suntime.ui.utils.ResourceStatus.*
import com.steru.suntime.ui.utils.TimeFormatter
import java.time.LocalDate

/**
 * [RecyclerView.Adapter] that can display a [FormattedSunData].
 */
class SunTimeListAdapter(
    private val values: MutableList<Resource<SunData>>,
    private val context: Context?
) : RecyclerView.Adapter<SunTimeListAdapter.ViewHolder>() {

    private val timeFormatter = TimeFormatter()

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
        data?.let {
            // every value contains date
            val date = timeFormatter.formatFullDateTimeToDate(it.sunrise)
            holder.dateText.text = date
            holder.sunriseText.text = timeFormatter.formatTo24hLocalTime(it.sunrise)
            holder.sunsetText.text = timeFormatter.formatTo24hLocalTime(it.sunset)

            setupTodayHighlight(holder, date)
        }
    }

    private fun setupTodayHighlight(
        holder: ViewHolder,
        dateString: String?
    ) {
        // todo keep the time values as time formats, not Strings to avoid parsing every time
        if (LocalDate.from(timeFormatter.dateFormat.parse(dateString)) == LocalDate.now()) {
            context?.let {
                holder.itemView.background =
                    ContextCompat.getDrawable(context, R.drawable.selected_rectangle)
            }
        }
    }

    private fun setErrorState(message: String?, holder: ViewHolder) {
        holder.spinner.visibility = View.GONE
        holder.errorText.text = context?.getString(R.string.fetching_error, message)
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