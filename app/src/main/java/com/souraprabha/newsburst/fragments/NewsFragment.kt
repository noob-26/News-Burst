package com.souraprabha.newsburst.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.souraprabha.newsburst.AgencyItem
import com.souraprabha.newsburst.AgencyItemAdapter
import com.souraprabha.newsburst.R

class NewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val gridView: GridView = view.findViewById(R.id.entertainment_gridview)

        val data: MutableList<AgencyItem> = ArrayList()

        data.add(AgencyItem("The Telegraph", R.drawable.telegraph_logo, "telegraphindia.com"))
        data.add(AgencyItem("The Verge", R.drawable.verge_logo, "theverge.com"))
        data.add(AgencyItem("ESPN", R.drawable.espn_logo, "espn.com"))
        data.add(AgencyItem("The Hindu", R.drawable.hindu_logo, "thehindu.com"))
        data.add(AgencyItem("Forbes", R.drawable.forbes_logo, "forbes.com"))
        data.add(AgencyItem("Cnet", R.drawable.cnet_logo, "cnet.com"))
        data.add(AgencyItem("The Washington Post", R.drawable.washington_post_logo, "washingtonpost.com"))
        data.add(AgencyItem("The Athletic", R.drawable.athletic_logo, "theathletic.com"))
        data.add(AgencyItem("Wired", R.drawable.wired_logo, "wired.com"))
        data.add(AgencyItem("The Next Web", R.drawable.the_next_web_logo, "thenextweb.com"))

        gridView.adapter = context?.let { AgencyItemAdapter(data, it) }
        return view
    }
}