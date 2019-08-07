package com.nm.keywordlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_keyword.view.*

class KeywordAdapter(val context: Context, var items : ArrayList<String>) : RecyclerView.Adapter<KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_keyword, parent, false))
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.keywordTextView.text = items[position]
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    private fun convertString() {

    }
}

class KeywordViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val keywordTextView = view.tvName
}