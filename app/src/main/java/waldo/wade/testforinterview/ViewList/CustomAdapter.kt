package waldo.wade.testforinterview.ViewList

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

import waldo.wade.testforinterview.R
import waldo.wade.testforinterview.UserLauncher
import java.io.IOException
import java.net.URL


internal class CustomAdapter(mContext: Context, private var itemsList: List<UserItem>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    var mUserHandlerThread: HandlerThread? = null
    var mUserHandler: Handler? = null
    var mContext: Context? = null

    init {
        mUserHandlerThread = HandlerThread("mJsoupHandlerThread");
        mUserHandlerThread!!.start();
        mUserHandler = Handler(mUserHandlerThread!!.looper)
        this.mContext = mContext
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
        val mAvatarImageView: ImageView = view.findViewById(R.id.AvatarImageView)


    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.login

        //post head image
        LoadImageTask(holder.mAvatarImageView).execute(item.avatar_url)


        //pass important values to Userlaucher 
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var mUserLauncher = UserLauncher(
                    mContext!!,
                    item.avatar_url,
                    item.login,
                    item.site_admin
                )
                //UserLauncher starts DetailActivity
                mUserLauncher.detailUserInfo()
            }

        });
    }

    override fun getItemCount(): Int {
        return itemsList.size
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