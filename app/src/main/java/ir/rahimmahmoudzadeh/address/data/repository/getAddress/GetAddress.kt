package ir.rahimmahmoudzadeh.address.data.repository.getAddress

import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

interface GetAddress {
    suspend fun getAddress():List<LocationInformation>
}