package ir.rahimmahmoudzadeh.address.data.repository.addAddress

import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

interface AddAddress {
    suspend fun addAddress(locationInformation: LocationInformation):Boolean
}