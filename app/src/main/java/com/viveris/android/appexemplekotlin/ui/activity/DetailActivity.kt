package com.viveris.android.appexemplekotlin.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.viveris.android.appexemplekotlin.R
import com.viveris.android.appexemplekotlin.model.User
import com.viveris.android.appexemplekotlin.utlis.DateUtils.Companion.dateToString
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.extras?.let { bundle ->
            (bundle.getSerializable(BUNDLE_EXTRA_USER) as? User?)?.let { user ->
                initializeView(user)
            }
        }
    }

    private fun initializeView(user: User) {
        text_view_user_alias.text = user.displayName
        text_view_user_location.text = user.location
        text_view_user_creation_date.text = getString(R.string.creation_date, dateToString(user.creationDate))
        Glide.with(this).load(user.profileImage).into(image_view_profile)
        user.badgeCounts?.let {
            text_view_gold_badge.text = it.gold.toString()
            text_view_silver_badge.text = it.silver.toString()
            text_view_bronze_badge.text = it.bronze.toString()
        }

        text_view_user_website.setOnClickListener { openBrowser(user) }
    }

    private fun openBrowser(user: User) {
        val browserIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(user.websiteUrl)
        }

        startActivity(browserIntent)
    }

    companion object {
        const val BUNDLE_EXTRA_USER = "BUNDLE_EXTRA_USER"
    }
}