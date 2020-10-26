package com.steru.suntime.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.steru.suntime.data.model.FormattedSunData
import com.steru.suntime.data.repository.SunRepository
import com.steru.suntime.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val sunRepository = SunRepository()

    val sunDataList = arrayListOf(
            FormattedSunData("10.01","1", "1"),
            FormattedSunData("10.02","2", "1"),
            FormattedSunData("10.03","3", "1")
    )

    val sunData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emit(sunRepository.getSunriseSunsetTimes(51f,17f))
    }
}