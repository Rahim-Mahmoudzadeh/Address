package ir.rahimmahmoudzadeh.address.ui.home

import androidx.lifecycle.*
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddress
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class HomeViewModel(val getAddress: GetAddress) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<Resource<List<LocationInformation>>>()
    val getAddressLiveData: LiveData<Resource<List<LocationInformation>>> = _mutableLiveData

    init {
        getAddress()
    }

    fun getAddress() {
        viewModelScope.launch {
            getAddress.getAddress().collect {
                _mutableLiveData.value = it
            }
        }
    }

}