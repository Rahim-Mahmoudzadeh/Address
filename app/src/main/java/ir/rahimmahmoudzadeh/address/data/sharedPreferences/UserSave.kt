package ir.rahimmahmoudzadeh.address.data.sharedPreferences

import android.content.BroadcastReceiver
import android.content.Context
import android.content.SharedPreferences

class UserSave(val context: Context) {
    private val userSharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    // save User password and mobile in shared preferences
    fun saveUser(mobile: String, password: String) {
        val edit = userSharedPreferences.edit()
        edit.putString("mobile", mobile)
        edit.putString("password", password)
        edit.apply()
    }

    // delete all value in shared preferences
    fun delete() {
        userSharedPreferences.edit().clear().apply()
    }

    // getMobile user value in shared preferences
    fun getMobile(): String = userSharedPreferences.getString("mobile", "").toString()

    // getPassword user value in shared preferences
    fun getPassword(): String = userSharedPreferences.getString("password", "").toString()
}