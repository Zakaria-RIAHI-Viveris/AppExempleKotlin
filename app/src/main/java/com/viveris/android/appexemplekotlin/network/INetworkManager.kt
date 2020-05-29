package com.viveris.android.appexemplekotlin.network

import com.viveris.android.appexemplekotlin.AppExempleKotlinApplication

interface INetworkManager {

    fun fetchUserFromNetwork(application: AppExempleKotlinApplication)
}