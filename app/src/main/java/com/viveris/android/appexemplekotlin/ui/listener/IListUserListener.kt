package com.viveris.android.appexemplekotlin.ui.listener

import com.viveris.android.appexemplekotlin.model.User
import com.viveris.android.appexemplekotlin.model.Users

interface IListUserListener {

    fun onUserClicked(user: User)
    fun displayFailure()
    fun displayResult(body: Users)

}