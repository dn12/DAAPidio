

package net.github.dn12.dnapidio

import android.app.Application
import net.github.dn12.dnapidio.di.networkModule
import net.github.dn12.dnapidio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    viewModelModule
                )
            )
        }
    }
}
