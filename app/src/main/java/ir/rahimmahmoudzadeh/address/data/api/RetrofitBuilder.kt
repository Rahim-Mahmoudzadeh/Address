package ir.rahimmahmoudzadeh.address.data.api

import ir.rahimmahmoudzadeh.address.data.sharedPreferences.UserSave
import ir.rahimmahmoudzadeh.address.ui.login.LoginFragment
import ir.rahimmahmoudzadeh.address.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private fun getRetrofit(userSave: UserSave): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(userSave))
            .addInterceptor {
                val oldRequest = it.request()
                val newRequest = oldRequest.newBuilder()
                newRequest.addHeader("Content-Type", "application/json")
                newRequest.method(oldRequest.method, oldRequest.body)
                return@addInterceptor it.proceed(newRequest.build())
            }
           client.connectTimeout(30, TimeUnit.SECONDS)
           client.readTimeout(30, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    fun apiService(userSave: UserSave): ApiService =
        getRetrofit(userSave).create(ApiService::class.java)
}