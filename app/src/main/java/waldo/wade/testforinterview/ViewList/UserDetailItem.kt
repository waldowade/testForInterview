package waldo.wade.testforinterview.ViewList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import waldo.wade.testforinterview.DetailActivity

class UserDetailItem(
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
        newExerciseDialogStream(mContext!!, "${avatar_url} mMessage: String",
            "${login} PosBtnLabel: String",
            "${site_admin} NegBtnLabel: String"
        )
    }
    class UserDetailRunnable(url: String) : Runnable {
        override fun run() {
            TODO("Not yet implemented")

        }
    }

    fun newExerciseDialogStream(
        mContext: Context, mMessage: String,
        PosBtnLabel: String,
        NegBtnLabel: String
    ) {
Log.d(TAG,"newExerciseDialogStream ${mMessage} ${PosBtnLabel} ${NegBtnLabel}")
        val myIntent = Intent(mContext, DetailActivity::class.java).apply {
            putExtra("EXTRA_mMessage", mMessage)
            putExtra("EXTRA_PosBtnLabel", PosBtnLabel)
            putExtra("EXTRA_NegBtnLabel", NegBtnLabel)
        }
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mContext,myIntent!!,null)
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
