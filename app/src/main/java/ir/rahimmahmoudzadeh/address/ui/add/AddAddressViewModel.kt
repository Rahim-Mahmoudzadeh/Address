package ir.rahimmahmoudzadeh.address.ui.add

import androidx.lifecycle.*
import ir.rahimmahmoudzadeh.address.data.model.LocationCreatedItem
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.repository.addAddress.AddAddress
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import retrofit2.HttpException

class AddAddressViewModel(val addAddress: AddAddress) : ViewModel() {
    var address: String?=null
    var coordinateMobile: String? = null
    var coordinatePhoneNumber: String? = null
    var firstName: String? = null
    var gender: String? = null
    var lastName: String? = null
    var lat: Double? = null
    var lng: Double? = null

    fun setAddressInformation(
        addressUser: String, coordinateMobileUser: String, coordinatePhoneNumberUser: String,
        firstNameUser: String, genderUser: String, lastNameUser: String
    ) {
        address = addressUser
        coordinateMobile = coordinateMobileUser
        coordinatePhoneNumber = coordinatePhoneNumberUser
        firstName = firstNameUser
        gender = genderUser
        lastName = lastNameUser
    }

    fun setAddressLocation(latUser: Double, lngUser: Double) {
        lat = latUser
        lng = lngUser
    }

    fun checkSaveAddress(): LiveData<Resource<LocationCreatedItem>> = liveData {
        try {
            emit(Resource.Loading())
            val isSuccess = addAddress.addAddress(
                LocationCreatedItem(
                    address.toString(),
                    lat!!.toDouble(),
                    lng!!.toDouble(),
                    coordinateMobile.toString(),
                    coordinatePhoneNumber.toString(),
                    firstName.toString(),
                    lastName.toString(),
                    gender.toString()
                )
            )
            emit(Resource.Success(isSuccess))

        } catch (e: HttpException) {
            emit(Resource.Error(convertErrorBody(e)))
        }
    }


}