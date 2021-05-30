package com.souraprabha.newsburst

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.souraprabha.newsburst.api.Articles


class NewsItemAdapter(private val data: MutableList<Articles>, private val context: Context): RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.news_image)
        val heading: TextView = view.findViewById(R.id.news_headline)
        val agency: TextView = view.findViewById(R.id.agency)
        val time: TextView = view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item: Articles = data[position]
        Glide.with(context)
            .load(item.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.loader)
            .into(holder.img)

        holder.heading.text = item.title
        if(item.author == null)
            holder.agency.text = "Anonymous"
        else
            holder.agency.text = item.author

        val indext = item.time.toString().indexOf('T')
        val lastsemi = item.time.toString().lastIndexOf(':')
        val str = item.time.toString().substring(indext + 1, lastsemi)

        holder.time.text = str
        holder.itemView.setOnClickListener{
            val url = item.url
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder();
            val customTabsIntent: CustomTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}