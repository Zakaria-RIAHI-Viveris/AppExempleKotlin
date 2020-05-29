package com.viveris.android.appexemplekotlin.network.retrofit

import com.viveris.android.appexemplekotlin.AppExempleKotlinApplication
import com.viveris.android.appexemplekotlin.network.INetworkManager
import com.viveris.android.appexemplekotlin.ui.listener.IListUserListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RxRetrofitNetworkManager(
    private val listener: IListUserListener,
    private val compositeDisposable: CompositeDisposable
) : INetworkManager {

    override fun fetchUserFromNetwork(application: AppExempleKotlinApplication) {
        compositeDisposable.add(application.retrofit.create(RequestInterface::class.java).fetchRxUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    if (users.userList != null) {
                        listener.displayResult(users)
                    } else {
                        listener.displayFailure()
                    }
                },
                { listener.displayFailure() }
            ))
    }

}