package ir.rahimmahmoudzadeh.address.data.repository.checkUser

import androidx.lifecycle.LiveData
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.utils.Resource
import kotlinx.coroutines.flow.Flow


interface CheckUser {
    suspend fun checkUserSarver():List<LocationInformation>
}