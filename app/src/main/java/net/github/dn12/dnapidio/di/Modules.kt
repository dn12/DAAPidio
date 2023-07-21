

package net.github.dn12.dnapidio.di

import android.content.Context
import android.content.SharedPreferences

import net.github.dn12.dnapidio.ui.home.HomeViewModel
import net.github.dn12.dnapidio.util.Constants
import net.github.dn12.network.api.YoutubeService
import net.github.dn12.network.repositories.YoutubeRepository
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkModule = module {

    //region sharedpreference
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            androidApplication().packageName, Context.MODE_PRIVATE
        )
    }
    //endregion

    // region retrofit
    single {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(
                MoshiConverterFactory.create()
            ).build()
    }
    // endregion retrofit


    factory {
        get<Retrofit>().create<YoutubeService>()
    }
    single {
        YoutubeRepository(get())
    }


}


val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}
