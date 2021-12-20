package ir.rahimmahmoudzadeh.address.ui.login

import android.util.Log
import androidx.lifecycle.*
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.repository.checkUser.CheckUser
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.convertErrorBody
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class LoginViewModel(val checkUserInterface: CheckUser) : ViewModel() {

    fun checkUser():LiveData<Resource<List<LocationInformation>>> = liveData{
        try {
            emit(Resource.Loading())
            val getUser = checkUserInterface.checkUserSarver()
            emit(Resource.Success(getUser))
        } catch (e: HttpException) {
            emit(Resource.Error(convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error("Not Internet"))
        }
    }

}