package waldo.wade.testforinterview

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

class UsersFragment(pageIndicator:Int) : Fragment() {
    val TAG = this.javaClass.simpleName
    private var mActivity: MainActivity? = null
    private val itemsList = ArrayList<UserItem>()
    private lateinit var customAdapter: CustomAdapter

    var mJsoupHandlerThread: HandlerThread? = null
    var mJsoupHandler: Handler? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActivity = activity as MainActivity?
        val mFragmentView = LayoutInflater.from(context).inflate(R.layout.fragment_users, container, false)

        val recyclerView: RecyclerView = mFragmentView.findViewById(R.id.recyclerView)


        mJsoupHandlerThread = HandlerThread("mJsoupHandlerThread");
        mJsoupHandlerThread!!.start();
        mJsoupHandler = Handler(mJsoupHandlerThread!!.looper)

        customAdapter = CustomAdapter(mActivity!!.applicationContext, itemsList)
        val layoutManager = LinearLayoutManager(mActivity!!.applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()
        return mFragmentView
    }
    val mJsoupRunnable: Runnable = object : Runnable {
        override fun run() {
            mJsoupHandler!!.removeCallbacks(this)
            val doc: Document =
                Jsoup.connect("https://api.github.com/users").ignoreContentType(true).get()
            val resultString: String = doc.body().text()
            Log.d(TAG, "rrrrrresultString : ${resultString}")

            var mGson = Gson()
            val mUserItemArray: Array<UserItem> =
                mGson.fromJson(resultString, Array<UserItem>::class.java)
            when(pageIndicator){
                1->{
                    for (i in 0 until 20) {
                        itemsList.add(mUserItemArray[i])
                        Log.d(TAG, "iiiiiiii : ${i}")
                    }
                }
                2->{
                    for (i in 20 until mUserItemArray.size) {
                        itemsList.add(mUserItemArray[i])
                        Log.d(TAG, "iiiiiiii : ${i}")
                    }
                }
            }


            GlobalScope.launch(Dispatchers.Main) {
                customAdapter.notifyDataSetChanged()
            }


        }

    }

    private fun prepareItems() {
        mJsoupHandler!!.postDelayed(mJsoupRunnable, 100)
    }
}