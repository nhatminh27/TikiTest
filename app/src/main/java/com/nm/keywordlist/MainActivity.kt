package com.nm.keywordlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val listKeyword = ArrayList<String>()
    var keywordAdapter: KeywordAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init adapter
        keywordAdapter = KeywordAdapter(this, listKeyword)
        rvKeyword.adapter = keywordAdapter

        fetchKeyword()
    }

    private fun fetchKeyword() {
        RetrofitManager.service.fetchKeyword().enqueue(object: Callback<ArrayList<String>>{
            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                makeText(applicationContext, "fail", LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                listKeyword.clear()
                response.body()?.let { listKeyword.addAll(it) }
                keywordAdapter?.notifyDataSetChanged()
            }
        })
    }

}
