package ir.rahimmahmoudzadeh.address.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import retrofit2.HttpException

fun showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun convertErrorBody(throwable: HttpException): String {
        var errorMessage: String? = null
        try {
            var errorJsonObject = JSONObject(throwable.response()?.errorBody()!!.string())
            if (throwable.code() == 401) {
                errorMessage = errorJsonObject.getString("detail")
            } else if (throwable.code() == 403) {
                errorMessage = errorJsonObject.getJSONArray("phone").get(0).toString()
            }
            else if(throwable.code()==500)
            {
                errorMessage=errorJsonObject.getString("detail")
            }

            return errorMessage.toString()

        } catch (exception: Exception) {

        }
    return "خطای نا مشخص"
}