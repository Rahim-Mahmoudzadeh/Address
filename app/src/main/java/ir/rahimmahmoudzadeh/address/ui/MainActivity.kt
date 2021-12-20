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
}