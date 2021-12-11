package ir.rahimmahmoudzadeh.address.data.repository.checkUser

import ir.rahimmahmoudzadeh.address.utils.Resource


interface CheckUser {
    suspend fun checkUser():Boolean
}