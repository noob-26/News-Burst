package com.souraprabha.newsburst

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.souraprabha.newsburst.fragments.NewsFragmentDirections

class AgencyItemAdapter(private val data: MutableList<AgencyItem>, val con: Context): ArrayAdapter<AgencyItem>(con, 0, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.agency_item, parent, false)

        val item = data[position]
        val img: ImageView = view!!.findViewById(R.id.agency_image)
        val txt: TextView = view!!.findViewById(R.id.agency_name)

        txt.text = item.name

        Glide.with(context)
                .load(item.id)
                .into(img)

        view.setOnClickListener{
            val action = NewsFragmentDirections.actionEntertainmentFragment2ToNewsDisplayFragment(item.link)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
}