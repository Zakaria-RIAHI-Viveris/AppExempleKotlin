package com.viveris.android.appexemplekotlin.network.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.viveris.android.appexemplekotlin.model.Users
import com.viveris.android.appexemplekotlin.ui.listener.IListUserListener

class UsersBroadcastReceiver(private val listener: IListUserListener) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val users = intent.getSerializableExtra(NetworkService.BUNDLE_RESULT_USERS)
        when (users is Users) {
            true -> listener.displayResult(users)
            else -> listener.displayFailure()
        }
    }

    companion object {
        const val ACTION_RESP = "com.viveris.appexemple.intent.action.LIST_TO_DISPLAY"
    }

}