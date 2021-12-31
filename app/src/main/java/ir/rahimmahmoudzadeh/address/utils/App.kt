package ir.rahimmahmoudzadeh.address.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import ir.map.sdk_map.Mapir
import ir.rahimmahmoudzadeh.address.data.api.RetrofitBuilder
import ir.rahimmahmoudzadeh.address.data.db.RoomBuilder
import ir.rahimmahmoudzadeh.address.data.repository.addAddress.AddAddress
import ir.rahimmahmoudzadeh.address.data.repository.addAddress.AddAddressImpl
import ir.rahimmahmoudzadeh.address.data.repository.checkUser.CheckUser
import ir.rahimmahmoudzadeh.address.data.repository.checkUser.CheckUserImpl
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddress
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddressImpl
import ir.rahimmahmoudzadeh.address.data.sharedPreferences.UserSave
import ir.rahimmahmoudzadeh.address.ui.add.AddAddressViewModel
import ir.rahimmahmoudzadeh.address.ui.home.HomeViewModel
import ir.rahimmahmoudzadeh.address.ui.login.LoginViewModel
import ir.rahimmahmoudzadeh.address.utils.Constants.KEY_MAP
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Mapir.getInstance(this, KEY_MAP)
        val module = module {
            single { UserSave(this@App) }
            single { RetrofitBuilder.apiService(get()) }
            single { RoomBuilder.getInstance(this@App) }
            factory<CheckUser> { CheckUserImpl(get()) }
            factory { CheckUserImpl(get()) }
            factory<GetAddress> { GetAddressImpl(get(),get()) }
            factory<AddAddress> { AddAddressImpl(get()) }
            viewModel { AddAddressViewModel(get()) }
            viewModel { LoginViewModel(get()) }
            viewModel { HomeViewModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(module)
        }
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            @SuppressLint("SourceLockedOrientationActivity")
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }
}