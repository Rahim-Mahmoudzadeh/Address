package ir.rahimmahmoudzadeh.address.data.repository.getAddress

import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.data.db.AppDataBase
import ir.rahimmahmoudzadeh.address.data.model.LocationInformationEntity
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GetAddressImpl(val apiService: ApiService, val appDataBase: AppDataBase) : GetAddress {
    override suspend fun getAddress(): List<LocationInformationEntity> = apiService.getAddress()
}