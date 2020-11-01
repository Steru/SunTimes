package com.steru.suntime.di

import com.steru.suntime.data.repository.SunRepository
import com.steru.suntime.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    fun provideSunRepository(): SunRepository {
        return SunRepository()
    }
    single { provideSunRepository() }
}

val sunTimeRepository = listOf(repositoryModule)