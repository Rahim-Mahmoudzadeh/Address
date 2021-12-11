package ir.rahimmahmoudzadeh.address.data.api

import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("karfarmas/address")
    suspend fun getAddress():List<LocationInformation>

    @GET("karfarmas/address")
    suspend fun checkUser():Response<Unit>

    @POST("karfarmas/address")
    suspend fun addAddress(@Body locationInformation: LocationInformation):Response<Unit>
}