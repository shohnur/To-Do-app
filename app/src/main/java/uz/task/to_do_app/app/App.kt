package uz.task.to_do_app.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.task.to_do_app.di.dbModule
import uz.task.to_do_app.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dbModule,
                    viewModelModule
                )
            )
        }
    }
}