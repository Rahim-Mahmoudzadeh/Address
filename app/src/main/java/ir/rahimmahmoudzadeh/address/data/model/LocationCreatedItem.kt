package ir.rahimmahmoudzadeh.address.data.model


import com.google.gson.annotations.SerializedName

data class LocationCreatedItem(
    val address: String,
    val lat: Double,
    val lng: Double,
    @SerializedName("coordinate_mobile")
    val coordinateMobile: String,
    @SerializedName("coordinate_phone_number")
    val coordinatePhoneNumber: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val gender: String

)