package com.steru.suntime.di

import com.steru.suntime.data.repository.SunRepository
import com.steru.suntime.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(sunRepository = get()) }
}

val repositoryModule = module {
    single { SunRepository() }
}

val sunTimeApp = listOf(appModule, repositoryModule)