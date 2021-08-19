package com.enadun.blacetalk.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enadun.blacetalk.R
import com.enadun.blacetalk.adapters.MainAdapter
import com.enadun.blacetalk.model.Data
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val mainRecyclerView = view.findViewById<RecyclerView>(R.id.main_recycler_view)
        getData { isSuccess, data ->
            if (isSuccess && data != null) {
                activity?.runOnUiThread {
                    mainRecyclerView.adapter = MainAdapter(data)
                    mainRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }
        }
        return view
    }

    private fun getData(callback: (isSuccess: Boolean, data: Data?) -> Unit) {
        val connection: HttpURLConnection =
            URL("https://demo5533418.mockable.io/home").openConnection() as HttpURLConnection
        thread {
            try {
                val responseCode = connection.responseCode
                if (responseCode != 200) {
                    callback(false, null)
                    return@thread
                }
                val response = connection.inputStream.bufferedReader().readText()
                val jsonData = JSONObject(response)
                val data = Data(jsonData)
                callback(true, data)
            } catch (e: Exception) {
                callback(false, null)
            } finally {
                connection.disconnect()
            }
        }
    }
}