package ir.rahimmahmoudzadeh.address.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ir.rahimmahmoudzadeh.address.data.repository.checkUser.CheckUser
import ir.rahimmahmoudzadeh.address.data.repository.getAddress.GetAddress
import ir.rahimmahmoudzadeh.address.utils.Resource
import okio.IOException
import retrofit2.HttpException

class HomeViewModel(val checkUser: CheckUser):ViewModel() {
    val checkUserLiveData:LiveData<Resource<Boolean>> = liveData {
        try {
            emit(Resource.Loading())
            val checkUser=checkUser.checkUser()
            emit(Resource.Success(checkUser))
        }catch (e:HttpException)
        {

        }catch (e:IOException)
        {

        }
    }
}