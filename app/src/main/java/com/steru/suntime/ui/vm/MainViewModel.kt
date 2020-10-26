package com.steru.suntime.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.steru.suntime.data.model.SunData
import com.steru.suntime.data.repository.SunRepository
import com.steru.suntime.ui.utils.Resource
import com.steru.suntime.ui.utils.TimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.collections.ArrayList

/**
 * Range of plus/minus days that days list should contain
 */
const val DAYS_LIST_RANGE = 10


class MainViewModel : ViewModel() {

    // outputs
    /**
     * List containing LiveData with Sun server responses.
     */
    val sunDataList: ArrayList<LiveData<Resource<SunData>>> = ArrayList()


    private val sunRepository = SunRepository()
    private val dateList: ArrayList<LocalDate>


    init {
        dateList = getDatesRange(LocalDate.now())
        val dateFormatter = TimeFormatter().dateFormat

        viewModelScope.launch {
            for (date in dateList) {
                sunDataList.add(
                    liveData(Dispatchers.IO) {
                        emit(Resource.loading())

                        emit(
                            sunRepository.getSunriseSunsetTimes(
                                date.format(dateFormatter),
                                // hardcoded Wrocław location for now
                                51f,
                                17f
                            )
                        )
                    })
            }
        }
    }

    private fun getDatesRange(today: LocalDate): ArrayList<LocalDate> {
        val list = ArrayList<LocalDate>()

        for (a in DAYS_LIST_RANGE downTo 1) {
            list.add(today.minusDays(a.toLong()))
        }

        list.add(today)

        for (a in 1..DAYS_LIST_RANGE) {
            list.add(today.plusDays(a.toLong()))
        }

        return list
    }

}