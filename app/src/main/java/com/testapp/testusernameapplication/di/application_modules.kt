package com.testapp.testusernameapplication.di

import android.util.Log
import com.testapp.testusernameapplication.dao.UserDao
import com.testapp.testusernameapplication.db.AppDatabase
import com.testapp.testusernameapplication.repository.UserRep
import com.testapp.testusernameapplication.repository.UserRepository
import com.testapp.testusernameapplication.repository.datasource.LocalDataStore
import com.testapp.testusernameapplication.repository.datasource.LocalUserRep
import com.testapp.testusernameapplication.repository.datasource.RemoteDataStore
import com.testapp.testusernameapplication.repository.datasource.RemoteUserRep
import com.testapp.testusernameapplication.service.UserService
import com.testapp.testusernameapplication.ui.registration.RegistrationViewModel
import com.testapp.testusernameapplication.ui.userInformation.UserInformationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://petstore.swagger.io/v2/"

val viewModule = module {
    viewModel { RegistrationViewModel(get()) }
    viewModel { UserInformationViewModel(get()) }
}

val data = module {

    single { createOkHttpClient() }

    single { createService<UserService>(get(), BASE_URL) }

    fun getUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    single { AppDatabase.getDatabase(androidContext()) }
    single { getUserDao(get()) }

    single { RemoteDataStore(get()) as RemoteUserRep }
    single { LocalDataStore(get()) as LocalUserRep }
    factory { UserRepository(get(), get()) as UserRep }
}

fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i("API", message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    Log.d("MSG", "here")
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(T::class.java)
}