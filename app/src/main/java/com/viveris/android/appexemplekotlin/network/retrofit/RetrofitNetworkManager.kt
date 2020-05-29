package com.viveris.android.appexemplekotlin.network.retrofit

import androidx.annotation.NonNull
import com.viveris.android.appexemplekotlin.AppExempleKotlinApplication
import com.viveris.android.appexemplekotlin.model.Users
import com.viveris.android.appexemplekotlin.network.INetworkManager
import com.viveris.android.appexemplekotlin.ui.listener.IListUserListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitNetworkManager(private val listener: IListUserListener) : INetworkManager {

    override fun fetchUserFromNetwork(application: AppExempleKotlinApplication) {
        val requestInterface: RequestInterface = application.retrofit.create(RequestInterface::class.java)

        requestInterface.fetchUsers().enqueue(object : Callback<Users> {
            override fun onResponse(@NonNull call: Call<Users>, @NonNull response: Response<Users>) {
                val body: Users? = response.body()
                when (body?.userList != null) {
                    true -> listener.displayResult(body!!)
                    else -> listener.displayFailure()
                }
            }

            override fun onFailure(@NonNull call: Call<Users?>, @NonNull t: Throwable) {
                listener.displayFailure()
            }
        })
    }

}