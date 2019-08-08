package com.nm.keywordlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val listKeyword = ArrayList<Keyword>()
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
        if (this.checkNetworkConnection()) {
            RetrofitManager.service.fetchKeyword().enqueue(object : Callback<ArrayList<String>> {
                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    makeText(applicationContext, "Fail to fetch keyword", LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                    listKeyword.clear()
                    response.body()?.let {
                        for (key in it) {
                            //Random color for each Keyword
                            val androidColors = resources.getIntArray(R.array.keywordColors)
                            val randomKeywordColor = androidColors[Random.nextInt(androidColors.size)]
                            listKeyword.add(Keyword(key, randomKeywordColor))
                        }
                    }
                    keywordAdapter?.notifyDataSetChanged()
                }
            })
        } else makeText(applicationContext, "No internet connection", LENGTH_LONG).show()

    }

}
