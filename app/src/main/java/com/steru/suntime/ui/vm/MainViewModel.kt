package com.steru.suntime.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.steru.suntime.data.repository.SunRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val sunRepository = SunRepository()

    val sunData = liveData(Dispatchers.IO) {
        val data = sunRepository.getSunriseSunsetTimes(50f, 20f)
        emit(data)
    }
}