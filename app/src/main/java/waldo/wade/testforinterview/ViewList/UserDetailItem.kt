package waldo.wade.testforinterview.ViewList

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class UserDetailItem(mContext: Context?) {
    val TAG = this.javaClass.simpleName
    var mContext: Context? = null
    var login: String? = null
    var id: String? = null
    var node_id: String? = null
    var avatar_url: String? = null
    var gravatar_id: String? = null
    var url: String? = null
    var html_url: String? = null
    var followers_url: String? = null
    var following_url: String? = null
    var gists_url: String? = null
    var starred_url: String? = null
    var subscriptions_url: String? = null
    var organizations_url: String? = null
    var repos_url: String? = null
    var events_url: String? = null
    var received_events_url: String? = null
    var type: String? = null
    var site_admin: String? = null
    var name: String? = null
    var company: String? = null
    var blog: String? = null
    var location: String? = null
    var email: String? = null
    var hireable: String? = null
    var bio: String? = null
    var twitter_username: String? = null
    var public_repos: String? = null
    var public_gists: String? = null
    var followers: String? = null
    var following: String? = null
    var created_at: String? = null
    var updated_at: String? = null

    init {
        this.mContext = mContext
    }

}