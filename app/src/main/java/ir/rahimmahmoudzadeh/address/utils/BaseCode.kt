package ir.rahimmahmoudzadeh.address.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ir.rahimmahmoudzadeh.address.R
import org.json.JSONObject
import retrofit2.HttpException

abstract class AddressFragment : Fragment(), AddressView {
    override val rootView: ConstraintLayout?
        get() = view as ConstraintLayout
    override val viewContext: Context?
        get() = context
}

interface AddressView {
    val rootView: ConstraintLayout?
    val viewContext: Context?
    fun showProgressBar(show: Boolean) {
        rootView?.let { rootView ->
            viewContext?.let { context ->
                var loadingView = rootView.findViewById<View>(R.id.loadingView)
                if (loadingView == null && show) {
                    loadingView =
                        LayoutInflater.from(context).inflate(R.layout.view_loading, rootView, false)
                    rootView.addView(loadingView)
                }
                loadingView?.visibility = if (show) View.VISIBLE else View.GONE
            }
        }
    }

}

@SuppressLint("WrongConstant")
fun showSnackBar(view: View, message: String) {
    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    ViewCompat.setLayoutDirection(snackbar.view, ViewCompat.LAYOUT_DIRECTION_RTL)
    snackbar.show()

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
        return errorMessage.toString()

    } catch (exception: Exception) {

    }
    return "خطای نا مشخص"
}