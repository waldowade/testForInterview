package waldo.wade.testforinterview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class UserLauncher(
    mContext: Context,
    avatar_url: String?,
    //user_name: String?,
    //bio: String?,
    login: String?,
    site_admin: String?,
    //location: String?,
    //blog: String?
) {
    val TAG = this.javaClass.simpleName
    var mContext: Context? = null

    init {
        this.mContext = mContext

    }

    var avatar_url: String? = avatar_url
        get() = field
        set(value) {
            field = value
        }

    //    var user_name: String? = user_name
//        get() = field
//        set(value) {
//            field = value
//        }
//    var bio: String? = bio
//        get() = field
//        set(value) {
//            field = value
//        }
    var login: String? = login
        get() = field
        set(value) {
            field = value
        }
    var site_admin: String? = site_admin
        get() = field
        set(value) {
            field = value
        }
//    var location: String? = location
//        get() = field
//        set(value) {
//            field = value
//        }
//    var blog: String? = blog
//        get() = field
//        set(value) {
//            field = value
//        }

    fun detailUserInfo()
    {
        newDetailActivity(mContext!!, "${avatar_url}",
            "${login}",
            "${site_admin}"
        )
    }
    fun newDetailActivity(
        mContext: Context, mAvatarUrl: String,
        mLogin: String,
        mSiteAdmin: String
    ) {

        var myIntent = Intent()
        myIntent.setClass(mContext,DetailActivity().javaClass)
        val mBundle = Bundle()
        // 傳參
        mBundle.putString("EXTRA_mAvatarUrl", mAvatarUrl)
        mBundle.putString("EXTRA_mLogin", mLogin)
        mBundle.putString("EXTRA_mSiteAdmin", mSiteAdmin)
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        myIntent.putExtras(mBundle)
        mContext.startActivity(myIntent)
    }

}
