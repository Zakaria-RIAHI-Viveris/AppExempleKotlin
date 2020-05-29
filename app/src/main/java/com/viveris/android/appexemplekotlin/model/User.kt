package com.viveris.android.appexemplekotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class User : Serializable {
    @SerializedName("badge_counts")
    @Expose
    var badgeCounts: BadgeCounts? = null
    @SerializedName("account_id")
    @Expose
    var accountId: Int? = null
    @SerializedName("last_modified_date")
    @Expose
    var lastModifiedDate: Int? = null
    @SerializedName("last_access_date")
    @Expose
    var lastAccessDate: Int? = null
    @SerializedName("reputation")
    @Expose
    var reputation: Int? = null
    @SerializedName("creation_date")
    @Expose
    var creationDate: Int? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("accept_rate")
    @Expose
    var acceptRate: Int? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("website_url")
    @Expose
    var websiteUrl: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: String? = null
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null

}