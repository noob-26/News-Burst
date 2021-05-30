package com.souraprabha.newsburst.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.souraprabha.newsburst.NewsItemAdapter
import com.souraprabha.newsburst.R
import com.souraprabha.newsburst.api.Articles
import com.souraprabha.newsburst.api.NewsApi
import com.souraprabha.newsburst.api.NewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlinesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_headlines, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.headlines_recyclerView)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val data: MutableList<Articles> = ArrayList()
        val call = NewsApi.api.getHeadlines("in", "ba81051033044d248eb5d6a4d9eca9b6")

        call.enqueue(object: Callback<NewsItem>{
            override fun onResponse(call: Call<NewsItem>, response: Response<NewsItem>) {
                if(!response.isSuccessful){
                    val ERROR: String = "Error occured !! Check your Internet"
                    Toast.makeText(context, ERROR, Toast.LENGTH_SHORT).show()
                } else {
                    val articles = response.body()
                    Log.d("debugged", response.body().toString())
                    if(articles!=null){
                        for(article in articles.articles){
                            data.add(Articles(article.author, article.title, article.url, article.urlToImage, article.time))
                        }
                        recyclerView.adapter = context?.let { NewsItemAdapter(data, it) }
                        recyclerView.adapter?.notifyDataSetChanged()
                        progressBar.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<NewsItem>, t: Throwable) {
                val ERROR: String = "Error occured !! Check your Internet"
                Toast.makeText(context, ERROR, Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = context?.let { NewsItemAdapter(data, it) }
        return view
    }
}
