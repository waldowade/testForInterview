package waldo.wade.testforinterview.ViewList
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import waldo.wade.testforinterview.R
import java.io.IOException
import java.net.URL


internal class CustomAdapter(mContext: Context, private var itemsList: List<UserItem>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    var mUserHandlerThread: HandlerThread? = null
    var mUserHandler: Handler? = null
    var mContext:Context? = null
    init{
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
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }

    class UserRunnable(url:String):Runnable{
        override fun run() {
            TODO("Not yet implemented")

        }
    }
    class LoadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>()
    {
        override fun doInBackground(vararg urls: String): Bitmap?
        {
            try {
                val stream = URL(urls[0]).openStream()

                return BitmapFactory.decodeStream(stream)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(bitmap: Bitmap?)
        {
            imageView.setImageBitmap(bitmap)
        }
    }

//    val mJsoupRunnable:Runnable = object:Runnable{
//        override fun run() {
//            mJsoupHandler!!.removeCallbacks(this)
//            val doc: Document =
//                Jsoup.connect("https://api.github.com/users").ignoreContentType(true).get()
//            val resultString: String = doc.body().text()
//            Log.d(TAG,"rrrrrresultString : ${resultString}")
//
//            var mGson = Gson()
//            val mUserItemArray: Array<UserItem> = mGson.fromJson(resultString, Array<UserItem>::class.java)
//            for(i in mUserItemArray.indices)
//            {
//                itemsList.add(mUserItemArray[i].avatar_url.toString())
//            }
//
//            GlobalScope.launch (Dispatchers.Main){
//                customAdapter.notifyDataSetChanged()
//            }
//
//
//        }
}