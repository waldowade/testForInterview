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

    /*constructor(name: String, age: Int, point: Int) : this(name, age) {
        this.user_name = point
    }*/
    fun detailUserInfo()
    {
        newDetailActivity(mContext!!, "${avatar_url}",
            "${login}",
            "${site_admin}"
        )
    }
    class UserDetailRunnable(url: String) : Runnable {
        override fun run() {
            TODO("Not yet implemented")

        }
    }

    fun newDetailActivity(
        mContext: Context, mAvatarUrl: String,
        mLogin: String,
        mSiteAdmin: String
    ) {
Log.d(TAG,"newDetailActivity ${mAvatarUrl} ${mLogin} ${mSiteAdmin}")
//        val myIntent = Intent(mContext, DetailActivity::class.java)
//        var mBundle:Bundle = Bundle()
//        mBundle.putString("EXTRA_mAvatarUrl", mAvatarUrl)
//        mBundle.putString("EXTRA_mLogin", mLogin)
//        mBundle.putString("EXTRA_mSiteAdmin", mSiteAdmin)
//        myIntent.putExtras(mBundle)
//        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(mContext,myIntent!!,null)

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

//        val editText = EditText(mContext)
//        if (PosBtnLabel.equals("添加"))
//            editText.setText("伏地挺身")
//        else if (PosBtnLabel.equals("完成"))
//            editText.setText("25")
//        MaterialAlertDialogBuilder(mContext)
//            .setMessage(mMessage)
//            .setView(editText)
//            .setPositiveButton(PosBtnLabel) { dialog, _ ->
//                if (PosBtnLabel.equals("添加")) {
//                } else {
//                }
//
//                dialog.dismiss()
//            }
//            .setNegativeButton(NegBtnLabel) { dialog, _ ->
//                dialog.dismiss()
//            }
//            .show()
    }

}
