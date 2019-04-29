package com.jml.random.users.di

import com.jml.random.users.BuildConfig
import com.jml.random.users.users.data.datasource.RandomUsersApi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//const val DEFAULT_NAMESPACE: Qualifier = "default"

val networkModule = module {

    single<OkHttpClient> /*(DEFAULT_NAMESPACE)*/ { provideDefaultOkhttpClient() }
    single<Retrofit> /*(DEFAULT_NAMESPACE)*/ { provideRetrofit(get(/*DEFAULT_NAMESPACE*/)) }
    single<RandomUsersApi> { provideRandomUsersApi(get(/*DEFAULT_NAMESPACE*/)) }

}

fun provideDefaultOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
       // .addInterceptor(ApiKeyInterceptor())
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideRandomUsersApi(retrofit: Retrofit): RandomUsersApi = retrofit.create(RandomUsersApi::class.java)

