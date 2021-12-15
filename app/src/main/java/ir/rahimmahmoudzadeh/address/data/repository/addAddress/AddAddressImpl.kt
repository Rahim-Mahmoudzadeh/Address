package ir.rahimmahmoudzadeh.address.data.repository.addAddress

import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

class AddAddressImpl(val apiService: ApiService):AddAddress {
    override suspend fun addAddress(locationInformation: LocationInformation): Boolean = apiService.addAddress(locationInformation).isSuccessful
}