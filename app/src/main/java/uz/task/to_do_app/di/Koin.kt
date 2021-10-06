package uz.task.to_do_app.di

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import uz.task.to_do_app.db.DB
import uz.task.to_do_app.db.Data
import uz.task.to_do_app.ui.AppViewModel

val dbModule = module {
    fun provideDatabase(application: Application): DB {
        return Room.databaseBuilder(application, DB::class.java, "ToDoList")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(db: DB) = db.dao()

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val viewModelModule = module {
    fun provideMutableLiveData() = MutableLiveData<Any>()

    viewModel { AppViewModel(get(),get()) }

    single { provideMutableLiveData() }
    single(named("data")) { MutableLiveData<Data>() }
}