package waldo.wade.testforinterview

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import waldo.wade.testforinterview.ViewList.CustomAdapter
import waldo.wade.testforinterview.ViewList.UserItem


class MainActivity : AppCompatActivity() {
    val TAG = "TestForInterview"
    private val itemsList = ArrayList<UserItem>()
    private lateinit var customAdapter: CustomAdapter

    var mJsoupHandlerThread: HandlerThread? = null
    var mJsoupHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Github users"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        mJsoupHandlerThread = HandlerThread("mJsoupHandlerThread");
        mJsoupHandlerThread!!.start();
        mJsoupHandler = Handler(mJsoupHandlerThread!!.looper)

        customAdapter = CustomAdapter(applicationContext,itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()
    }
    val mJsoupRunnable:Runnable = object:Runnable{
        override fun run() {
            mJsoupHandler!!.removeCallbacks(this)
            val doc: Document =
                Jsoup.connect("https://api.github.com/users").ignoreContentType(true).get()
            val resultString: String = doc.body().text()
            Log.d(TAG,"rrrrrresultString : ${resultString}")

            var mGson = Gson()
            val mUserItemArray: Array<UserItem> = mGson.fromJson(resultString, Array<UserItem>::class.java)
            for(i in mUserItemArray.indices)
            {
                itemsList.add(mUserItemArray[i])
            }

            GlobalScope.launch (Dispatchers.Main){
                customAdapter.notifyDataSetChanged()
            }


        }

    }
    private fun prepareItems() {
//        itemsList.add("Item 1")
//        itemsList.add("Item 2")
//        itemsList.add("Item 3")
//        itemsList.add("Item 4")
//        itemsList.add("Item 5")
//        itemsList.add("Item 6")
//        itemsList.add("Item 7")
//        itemsList.add("Item 8")
//        itemsList.add("Item 9")
//        itemsList.add("Item 10")
//        itemsList.add("Item 11")
//        itemsList.add("Item 12")
//        itemsList.add("Item 13")
//        customAdapter.notifyDataSetChanged()
        mJsoupHandler!!.postDelayed(mJsoupRunnable,100)
    }
}
