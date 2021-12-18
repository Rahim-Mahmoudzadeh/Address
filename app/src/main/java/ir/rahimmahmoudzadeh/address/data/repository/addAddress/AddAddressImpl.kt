package ir.rahimmahmoudzadeh.address.data.repository.addAddress

import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.data.model.LocationCreatedItem
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

class AddAddressImpl(val apiService: ApiService) : AddAddress {
    override suspend fun addAddress(locationInformation: LocationCreatedItem): LocationInformation =
        apiService.addAddress(locationInformation)
}