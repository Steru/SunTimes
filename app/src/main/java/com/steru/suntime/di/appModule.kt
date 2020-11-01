package com.steru.suntime.di

import com.steru.suntime.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(sunRepository = get()) }
}

val sunTimeApp = listOf(appModule)