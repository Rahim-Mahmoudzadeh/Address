package ir.rahimmahmoudzadeh.address.data.repository.checkUser

import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.utils.Resource

class CheckUserImpl(val apiService: ApiService):CheckUser {
    override suspend fun checkUser():Boolean = apiService.checkUser().isSuccessful
}