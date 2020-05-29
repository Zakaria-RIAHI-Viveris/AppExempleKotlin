package com.viveris.android.appexemplekotlin.network.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.viveris.android.appexemplekotlin.AppExempleKotlinApplication
import com.viveris.android.appexemplekotlin.model.Users
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.apache.commons.io.IOUtils
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection


class NetworkService : IntentService("NetworkService") {

    private var users: Users? = null

    override fun onHandleIntent(intent: Intent?) {
        timer()
        fetchUserFromNetwork(application as AppExempleKotlinApplication)
        returnResultToCaller()
    }

    private fun fetchUserFromNetwork(application: AppExempleKotlinApplication) {
        val gson = application.gson
        try {
            val url = URL(AppExempleKotlinApplication.BASE_URL + "users?order=desc&sort=reputation&site=stackoverflow")
            val myConnection = url.openConnection() as HttpsURLConnection
            val responseBody = myConnection.inputStream
            val body = IOUtils.toString(responseBody, "UTF-8")
            users = gson.fromJson(body, Users::class.java)
        } catch (e: IOException) {
            Log.e(this.javaClass.simpleName, "IOException", e)

        }
    }

    private fun returnResultToCaller() {
        val broadcastIntent = Intent().apply {
            action = UsersBroadcastReceiver.ACTION_RESP
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(BUNDLE_RESULT_USERS, users)
        }
        sendBroadcast(broadcastIntent)
    }

    private fun timer() {
        Observable.timer(5000, TimeUnit.MILLISECONDS)
            .repeat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(this, "timer", Toast.LENGTH_SHORT).show()
                Log.e(javaClass.simpleName, "timer")
            }
    }

    companion object {
        const val BUNDLE_RESULT_USERS = "BUNDLE_RESULT_USERS"
    }
}