package ir.rahimmahmoudzadeh.address.data.repository.checkUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class CheckUserImpl(val apiService: ApiService) : CheckUser {
    override suspend fun checkUserSarver(): List<LocationInformation> = apiService.checkUser()

}