package com.steru.suntime.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steru.suntime.data.model.SunData
import com.steru.suntime.data.repository.SunRepository
import com.steru.suntime.ui.utils.Resource
import com.steru.suntime.ui.utils.TimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.ext.getScopeName
import java.time.LocalDate


/**
 * Range of plus/minus days that days list should contain
 */
const val DAYS_LIST_RANGE = 8

/**
 * ViewModel class handling fetching of data, main list date range and outputting the sun data list
 */
class MainViewModel : ViewModel() {
    // todo use Koin to extract repository as parameter and test the vm

    // outputs
    /**
     * List containing LiveData with Sun server responses.
     */
    val sunDataList: ArrayList<Resource<SunData>> = ArrayList()

    /**
     * LiveData notifying the view that given item value was updated
     */
    val itemUpdated: MutableLiveData<Int> = MutableLiveData()

    private val sunRepository = SunRepository()
    private val dateList: ArrayList<LocalDate>


    init {
        dateList = getDatesRange(LocalDate.now())

        repeat(dateList.size) {
            sunDataList.add(Resource.loading())
        }

        fetchDataForDateRange()
    }

    private fun fetchDataForDateRange() {
        viewModelScope.launch {
            for (i in 0 until dateList.size) {
                launch(Dispatchers.IO) {
                    this.getScopeName()
                    fetchDataAndNotify(i, dateList[i])
                }
            }
        }
    }

    private suspend fun fetchDataAndNotify(i: Int, date: LocalDate) {
        val dateFormatter = TimeFormatter().dateFormat

        sunDataList[i] = sunRepository.getSunriseSunsetTimes(
            date.format(dateFormatter),
            // hardcoded Wrocław location for now
            51f,
            17f
        )
        itemUpdated.postValue(i)
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