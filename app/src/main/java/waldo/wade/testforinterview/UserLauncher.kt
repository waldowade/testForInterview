package waldo.wade.testforinterview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

//this class can be used for preparing important values calculation.
//currently ,no calculation
class UserLauncher(
    mContext: Context,
    avatar_url: String?,
    login: String?,
    site_admin: String?,
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

    fun detailUserInfo() {
        newDetailActivity(
            mContext!!, "${avatar_url}",
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
        myIntent.setClass(mContext, DetailActivity().javaClass)
        val mBundle = Bundle()

        //Put Important values to bundle then start DetailActivity
        mBundle.putString("EXTRA_mAvatarUrl", mAvatarUrl)
        mBundle.putString("EXTRA_mLogin", mLogin)
        mBundle.putString("EXTRA_mSiteAdmin", mSiteAdmin)
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        myIntent.putExtras(mBundle)
        mContext.startActivity(myIntent)
    }

}
