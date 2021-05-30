package com.souraprabha.newsburst.fragments

import android.os.Bundle
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

class TechnologyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_technology, container, false)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val recyclerView: RecyclerView = view.findViewById(R.id.technology_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val data: MutableList<Articles> = ArrayList()
        val call = NewsApi.api.getNews("in", "technology", "ba81051033044d248eb5d6a4d9eca9b6")

        call.enqueue(object: Callback<NewsItem> {
            override fun onResponse(call: Call<NewsItem>, response: Response<NewsItem>) {
                if(!response.isSuccessful){
                    val ERROR: String = "Error occured !! Check your Internet"
                    Toast.makeText(context, ERROR, Toast.LENGTH_SHORT).show()
                } else {
                    val articles = response.body()
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