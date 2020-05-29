package com.viveris.android.appexemplekotlin.network.retrofit

import com.viveris.android.appexemplekotlin.model.Users
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface RequestInterface {
    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchUsers(): Call<Users>

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchRxUsers(): Single<Users>
}