package ir.rahimmahmoudzadeh.address.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.rahimmahmoudzadeh.address.data.repository.addAddress.AddAddress

class AddAddressViewModel(val addAddress: AddAddress):ViewModel() {
    var address: String?=null
    var coordinateMobile: String?=null
    var coordinatePhoneNumber: String?=null
    var firstName: String?=null
    var gender: String?=null
    var lastName: String?=null
    var lat: Double? = null
    var lng: Double? = null

    val setAddressMutableLiveData = MutableLiveData<Boolean>()

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

}