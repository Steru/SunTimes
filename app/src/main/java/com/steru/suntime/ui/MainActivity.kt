package com.steru.suntime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.steru.suntime.R
import com.steru.suntime.ui.utils.ResourceStatus.*
import com.steru.suntime.ui.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.sunData.observe(this, { resource ->
            val textView = findViewById<TextView>(R.id.basicText)

            textView?.text = when (resource.status) {
                SUCCESS -> "basic fetch = ${resource.data?.sunrise}, ${resource.data?.sunset}"
                ERROR -> "ERROR! ${resource.message}"
                LOADING -> "Wait for it..."
            }
        })

    }
}