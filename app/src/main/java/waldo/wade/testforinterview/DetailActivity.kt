package waldo.wade.testforinterview

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import waldo.wade.testforinterview.ViewList.UserDetailItem
import java.io.IOException
import java.net.URL
import java.util.*


class DetailActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    var mUserImageView: ImageView? = null
    var backImageView: ImageView? = null
    var mbioImageView: ImageView? = null
    var mNameTextView: TextView? = null
    var mLocationTextView: TextView? = null
    var mUrlTextView: TextView? = null
    var mSingleUserName: String? = null
    var mDetailHandlerThread: HandlerThread? = null
    var mDetailHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = "Github users"
        mUserImageView = findViewById(R.id.UserImageView)
        backImageView = findViewById(R.id.backImageView)
        mbioImageView = findViewById(R.id.bioImageView)
        mNameTextView = findViewById(R.id.NameTextView)
        mLocationTextView = findViewById(R.id.LocationTextView)
        mUrlTextView = findViewById(R.id.UrlTextView)
        mDetailHandlerThread = HandlerThread("mDetailHandlerThread");
        mDetailHandlerThread!!.start();
        mDetailHandler = Handler(mDetailHandlerThread!!.looper)

        //post head image

        LoadImageTask(mUserImageView!!).execute(this.intent.extras!!.getString("EXTRA_mAvatarUrl"))
        backImageView!!.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                finish()
            }

        })
        mNameTextView!!.text = "nnnnnnnnnnnname"
        mLocationTextView!!.text = "lllllllocation"
        mSingleUserName = this.intent.extras!!.getString("EXTRA_mLogin")
        mUrlTextView!!.text = this.intent.extras!!.getString("EXTRA_mAvatarUrl")
        mDetailHandler!!.post(
            UserDetailRunnable(
                applicationContext,
                mSingleUserName!!,
                mNameTextView!!,
                mbioImageView!!,
                mLocationTextView!!,
                mUrlTextView!!,
                mDetailHandler!!
            )
        )
//        mBundle.putString("EXTRA_mAvatarUrl", mAvatarUrl)
//        mBundle.putString("EXTRA_mLogin", mLogin)
//        mBundle.putString("EXTRA_mSiteAdmin", mSiteAdmin)

//        LoadImageTask(mUserImageView).execute("https://avatars.githubusercontent.com/u/2?v=4")
    }

    class UserDetailRunnable(
        mContext:Context,
        mSingleUserName: String,
        mNameTextView: TextView,
        mbioImageView:ImageView,
        mLocationTextView: TextView,
        mUrlTextView: TextView,
        mHandler: Handler
    ) : Runnable {
        val TAG = this.javaClass.simpleName
        var mContext:Context? = null
        var mHandler: Handler? = null
        var mSingleUserName: String? = null
        var mNameTextView: TextView? = null
        var mbioImageView: ImageView? = null
        var mLocationTextView: TextView? = null
        var mUrlTextView: TextView? = null

        init {
            this.mContext = mContext
            this.mHandler = mHandler
            this.mSingleUserName = mSingleUserName
            this.mNameTextView = mNameTextView
            this.mbioImageView = mbioImageView
            this.mLocationTextView = mLocationTextView
            this.mUrlTextView = mUrlTextView
        }

        override fun run() {
            mHandler!!.removeCallbacks(this)
            val doc: Document =
                Jsoup.connect("https://api.github.com/users/" + mSingleUserName)
                    .ignoreContentType(true).get()
            val resultString: String = doc.body().text()
            Log.d(TAG, "resultStringggggggg : ${resultString}")

            var mGson = Gson()
            val mUserDetailItem: UserDetailItem =
                mGson.fromJson(resultString, UserDetailItem::class.java)


            GlobalScope.launch(Dispatchers.Main) {
                mNameTextView!!.text = "123123123"
                //if(mUserDetailItem.name.equals(mSingleUserName)){
                mNameTextView!!.text = mUserDetailItem.name
                mLocationTextView!!.text = mUserDetailItem.location
                mUrlTextView!!.text = mUserDetailItem.blog

                LoadImageTask(mbioImageView!!).execute(mUserDetailItem.bio)
                mUrlTextView!!.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(p0: View?) {
                        var browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mUserDetailItem.blog))
                        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        mContext!!.startActivity(browserIntent!!)

                    }
                })
            }
        }
    }
    class LoadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            try {
                val stream = URL(urls[0]).openStream()

                return BitmapFactory.decodeStream(stream)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            imageView.setImageBitmap(bitmap)
        }
    }


}
