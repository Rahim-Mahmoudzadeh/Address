package ir.rahimmahmoudzadeh.address.data.api

import androidx.lifecycle.LiveData
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.sharedPreferences.UserSave
import ir.rahimmahmoudzadeh.address.utils.Constants
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("karfarmas/address")
    suspend fun getAddress(): List<LocationInformation>

    @GET("karfarmas/address")
    suspend fun checkUser(): List<LocationInformation>

    @POST("karfarmas/address")
    suspend fun addAddress(@Body locationInformation: LocationInformation): Response<Unit>
}
