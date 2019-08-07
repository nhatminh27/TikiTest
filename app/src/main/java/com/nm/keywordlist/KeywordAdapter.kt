package com.nm.keywordlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_keyword.view.*

class KeywordAdapter(val context: Context, var items: ArrayList<String>) : RecyclerView.Adapter<KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_keyword, parent, false))
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.keywordTextView.text = convertString(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun convertString(str: String): String {
        val listString = str.toList()

        if (listString.size > 1) {
            // Case 1: String was 2 two word and length of word 1 less than length of word 2
            if (listString.size == 2 && listString[0].length < listString[1].length) {
                listString.addWrapToListString(1)
            }
            else if (listString.size > 2) {
                listString.addWrapToListString(getWrapIndex(listString))
            }
        }
        return listString.joinToString(separator = " ")
    }

    private fun getWrapIndex(list: List<String>): Int {
        //Index of wrap line
        var index = 0
        do {
            index++
            var lengthOfLine1 = 0
            var lengthOfLine2 = 0

            for (i in 0..index) lengthOfLine1 += list[i].length
            for (i in (index+1) until list.size) lengthOfLine2 += list[i].length
        } while (lengthOfLine1 < lengthOfLine2)

        return index
    }

}

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val keywordTextView = view.tvName
}