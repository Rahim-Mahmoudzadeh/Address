package ir.rahimmahmoudzadeh.address.data.repository.getAddress

import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GetAddressImpl(val apiService: ApiService) : GetAddress {
    override suspend fun getAddress(): Flow<Resource<List<LocationInformation>>> = flow {
        try {
            emit(Resource.Loading())
            val getAddress = apiService.getAddress()
            emit(Resource.Success(getAddress))
        } catch (e: HttpException) {
            emit(Resource.Error(convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error("Not Internet"))
        }
    }
}