package ir.rahimmahmoudzadeh.address.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddress
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import okio.IOException
import retrofit2.HttpException

class HomeViewModel(val getAddress: GetAddress):ViewModel() {
  fun getAddressLiveData():LiveData<Resource<List<LocationInformation>>> = liveData {
      try {
          emit(Resource.Loading())
          val getAddress=getAddress.getAddress()
          emit(Resource.Success(getAddress))
      }catch (e: HttpException)
      {
          emit(Resource.Error(convertErrorBody(e)))
      }
  }
}