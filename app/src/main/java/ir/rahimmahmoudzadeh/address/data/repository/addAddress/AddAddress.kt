package ir.rahimmahmoudzadeh.address.data.repository.addAddress

import ir.rahimmahmoudzadeh.address.data.model.LocationCreatedItem
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

interface AddAddress {
    suspend fun addAddress(locationCreatedItem: LocationCreatedItem):LocationInformation
}