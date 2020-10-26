package com.steru.suntime.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.steru.suntime.data.repository.SunRepository
import com.steru.suntime.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val sunRepository = SunRepository()

    val sunData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emit(sunRepository.getSunriseSunsetTimes(51f,17f))
    }
}