package ir.rahimmahmoudzadeh.address.data.repository.getAddress

import ir.rahimmahmoudzadeh.address.data.api.ApiService
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

class GetAddressImpl(val apiService: ApiService):GetAddress {
    override suspend fun getAddress(): List<LocationInformation> = apiService.getAddress()
}