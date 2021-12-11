package ir.rahimmahmoudzadeh.address.utils

import android.app.Application
import io.reactivex.Single
import ir.rahimmahmoudzadeh.address.data.api.RetrofitBuilder
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddress
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddressImpl
import ir.rahimmahmoudzadeh.address.data.sharedPreferences.UserSave
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val module = module {
            single { UserSave(this@App) }
            single { RetrofitBuilder.apiService(get()) }
            factory<GetAddress> { GetAddressImpl(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }
}