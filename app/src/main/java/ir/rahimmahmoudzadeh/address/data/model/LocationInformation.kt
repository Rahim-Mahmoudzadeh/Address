package ir.rahimmahmoudzadeh.address.data.model

data class LocationInformation(
    val address: String,
    val address_id: String?,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
    val first_name: String,
    val gender: String,
    val id: Int?,
    val last_name: String,
    val lat: Double,
    val lng: Double,
)