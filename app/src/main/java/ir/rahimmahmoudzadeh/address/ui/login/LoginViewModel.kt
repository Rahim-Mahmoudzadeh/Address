package ir.rahimmahmoudzadeh.address.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.repository.checkUser.CheckUser
import ir.rahimmahmoudzadeh.address.data.repository.checkUser.CheckUserImpl
import ir.rahimmahmoudzadeh.address.utils.AddressState
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import kotlinx.coroutines.flow.*
import okio.IOException
import retrofit2.HttpException

class LoginViewModel(val checkUserInterface: CheckUser) : ViewModel() {
    fun checkUser(): LiveData<Resource<List<LocationInformation>>> = liveData {
        try {
            emit(Resource.Loading())
            var getUser = checkUserInterface.checkUser()
            emit(Resource.Success(getUser))
        } catch (e: HttpException) {
            emit(Resource.Error(convertErrorBody(e)))
        }

    }
}