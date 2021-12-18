package ir.rahimmahmoudzadeh.address.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ir.rahimmahmoudzadeh.address.R
import ir.rahimmahmoudzadeh.address.ui.add.AddAddressViewModel
import ir.rahimmahmoudzadeh.address.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewMode by viewModel<AddAddressViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun isConnected(view: View, context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (network == null || !network?.isConnected) {
            showSnackBar(view, context.resources.getString(R.string.error_connection))
        }

        return network != null && network.isConnected
    }
}