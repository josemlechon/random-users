package com.jml.random.users.di

import androidx.room.Room
import com.jml.random.users.app.AppDataBase
import com.jml.random.users.users.domain.usecases.DeleteUser

import com.jml.random.users.home.domain.usecases.GetMoreHomeUsers
import com.jml.random.users.home.domain.usecases.FilterHomeUsers
import com.jml.random.users.home.view.vm.HomeViewModel
import com.jml.random.users.users.data.datasource.UserRemoteDataSource
import com.jml.random.users.users.domain.repository.UserRepository
import com.jml.random.users.users.data.repository.UserRepositoryImpl
import com.jml.random.users.home.domain.usecases.GetHomeUsers
import com.jml.random.users.users.domain.usecases.GetUser
import com.jml.random.users.users.view.vm.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { UserViewModel(get()) }

    // Room Database
    single { Room.databaseBuilder(get(), AppDataBase::class.java, AppDataBase.DATABASE_NAME).build() }
    single { get<AppDataBase>().userDao() }
    single { get<AppDataBase>().pagesDao() }
    single { get<AppDataBase>().deleteUserDao() }
}

val useCaseModule = module {
    factory { GetHomeUsers(get()) }
    factory { GetMoreHomeUsers(get()) }
    factory { FilterHomeUsers(get()) }
    factory { DeleteUser(get()) }
    factory { GetUser(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
    factory { UserRemoteDataSource(get()) }
}


