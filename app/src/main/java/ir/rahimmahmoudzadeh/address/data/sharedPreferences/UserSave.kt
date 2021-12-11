package ir.rahimmahmoudzadeh.address.data.sharedPreferences

import android.content.BroadcastReceiver
import android.content.Context
import android.content.SharedPreferences

class UserSave(context: Context) {
    private val userSharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun saveUser(mobile: String, password: String) {
        val edit = userSharedPreferences.edit()
        edit.putString("mobile", mobile)
        edit.putString("password", password)
        edit.apply()
    }

    fun delete() {
        userSharedPreferences.edit().clear().apply()
    }

    fun getMobile(): String = userSharedPreferences.getString("mobile", "").toString()
    fun getPassword(): String = userSharedPreferences.getString("password", "").toString()
}