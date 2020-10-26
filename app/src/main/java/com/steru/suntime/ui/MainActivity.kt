package com.steru.suntime.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.steru.suntime.R
import com.steru.suntime.data.model.SunData
import com.steru.suntime.ui.utils.Resource
import com.steru.suntime.ui.utils.ResourceStatus.*
import com.steru.suntime.ui.utils.TimeFormatter
import com.steru.suntime.ui.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.sunData.observe(this, { resource ->
            val textView = findViewById<TextView>(R.id.basicText)

            textView?.text = when (resource.status) {
                SUCCESS -> handleSuccessfulResource(resource)
                ERROR -> "ERROR! ${resource.message}"
                LOADING -> "Wait for it..."
            }
        })

    }

    private fun handleSuccessfulResource(resource: Resource<SunData>): CharSequence? {
        val formatter = TimeFormatter()
        resource.data?.apply {
            return "basic fetch = ${formatter.formatTo24hLocalTime(sunrise)}, " +
                    "${formatter.formatTo24hLocalTime(sunset)}, " +
                    "${formatter.formatSecondsToHours(dayLength)}\""
        }
        return null
    }
}