package ir.rahimmahmoudzadeh.address.data.api

import ir.rahimmahmoudzadeh.address.data.sharedPreferences.UserSave
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(var userSave: UserSave) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header(
            "Authorization",
            Credentials.basic(userSave.getMobile(), userSave.getPassword())
        ).build()
        return chain.proceed(request)
    }
}