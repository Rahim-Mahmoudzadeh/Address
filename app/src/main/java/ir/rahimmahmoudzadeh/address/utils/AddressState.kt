package ir.rahimmahmoudzadeh.address.utils

import ir.rahimmahmoudzadeh.address.data.model.LocationInformation

data class AddressState(
    val isLoading: Boolean = false,
    val address: List<LocationInformation> = emptyList(),
    val error: String = ""
)