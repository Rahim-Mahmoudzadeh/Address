package ir.rahimmahmoudzadeh.address.utils

import android.app.Application
import io.reactivex.Single
import ir.map.sdk_map.Mapir
import ir.rahimmahmoudzadeh.address.data.api.RetrofitBuilder
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
            single {RetrofitBuilder.apiService(get())}
            factory<CheckUser> {CheckUserImpl(get())}
            factory { CheckUserImpl(get()) }
            factory<GetAddress> { GetAddressImpl(get()) }
            factory<AddAddress> {AddAddressImpl(get())}
            viewModel{AddAddressViewModel(get())}
            viewModel{LoginViewModel(get())}
            viewModel{HomeViewModel(get())}
        }
        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }
}