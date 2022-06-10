package waldo.wade.testforinterview.ViewList

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

//Below values are all available for usages , after UserFragment gson parse was executed
class UserItem(mContext: Context?) {
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

    init {
        this.mContext = mContext
    }
}