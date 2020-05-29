package com.viveris.android.appexemplekotlin.ui.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.viveris.android.appexemplekotlin.AppExempleKotlinApplication
import com.viveris.android.appexemplekotlin.R
import com.viveris.android.appexemplekotlin.local.SharedPreferencesManager.Companion.PREF_KEY_ALIAS
import com.viveris.android.appexemplekotlin.local.SharedPreferencesManager.Companion.getSharedAppSharedPreference
import com.viveris.android.appexemplekotlin.model.User
import com.viveris.android.appexemplekotlin.model.Users
import com.viveris.android.appexemplekotlin.network.INetworkManager
import com.viveris.android.appexemplekotlin.network.service.NetworkService
import com.viveris.android.appexemplekotlin.network.service.UsersBroadcastReceiver
import com.viveris.android.appexemplekotlin.ui.adapter.UsersRecyclerAdapter
import com.viveris.android.appexemplekotlin.ui.listener.IListUserListener
import com.viveris.android.appexemplekotlin.ui.view.ProgressBarManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.annotations.Nullable


class ListActivity : AppCompatActivity(), IListUserListener {
    private var progressBarManager: ProgressBarManager? = null
    private var networkManager: INetworkManager? = null
    private var receiver: UsersBroadcastReceiver? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        btn_save_alias.setOnClickListener { saveMyUserAlias() }
        progressBarManager = ProgressBarManager()
//        networkManager = RetrofitNetworkManager(this)
//        networkManager = RxRetrofitNetworkManager(this, compositeDisposable)
        initializeData()
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(UsersBroadcastReceiver.ACTION_RESP)
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onUserClicked(user: User) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.BUNDLE_EXTRA_USER, user)
        })
    }

    override fun displayFailure() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
    }

    override fun displayResult(body: Users) {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)

        list_view_users?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListActivity, VERTICAL,false)
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            adapter = UsersRecyclerAdapter(body.userList!!, applicationContext, this@ListActivity)
        }
    }

    private fun initializeData() {
        val myAlias: String = getSharedAppSharedPreference(application).getString(PREF_KEY_ALIAS, "")?:""
        if (!TextUtils.isEmpty(myAlias)) {
            et_my_user_alias?.setText(myAlias)
        }
        progressBarManager?.onLoaderStateChange(true, progressBarHolder)
//        fetchUsers()
        fetchUsersWithService()
    }

    private fun saveMyUserAlias() {
        val myUserAlias = et_my_user_alias?.text.toString()
        if (!TextUtils.isEmpty(myUserAlias)) {
            val editor = getSharedAppSharedPreference(application).edit()
            editor.putString(PREF_KEY_ALIAS, myUserAlias).apply()
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchUsers() {
        val application: AppExempleKotlinApplication = application as AppExempleKotlinApplication
        networkManager?.fetchUserFromNetwork(application)
    }

    private fun fetchUsersWithService() { // on initialise notre broadcast
        receiver = UsersBroadcastReceiver(this)
        // on lance le service
        val msgIntent = Intent(this, NetworkService::class.java)
        startService(msgIntent)
    }
}