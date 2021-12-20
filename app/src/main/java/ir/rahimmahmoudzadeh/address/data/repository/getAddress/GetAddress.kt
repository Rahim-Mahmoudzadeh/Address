package ir.rahimmahmoudzadeh.address.data.repository.getAddress

import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetAddress {
    suspend fun getAddress(): Flow<Resource<List<LocationInformation>>>
}