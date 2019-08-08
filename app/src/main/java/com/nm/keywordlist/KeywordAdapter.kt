package com.nm.keywordlist

import android.content.Context
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_keyword.view.*
import kotlin.random.Random


class KeywordAdapter(val context: Context, var items: ArrayList<Keyword>) : RecyclerView.Adapter<KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_keyword, parent, false))
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.keywordTextView.text = convertString(items[position].name)
        holder.keywordLinearLayour.background.setColorFilter(items[position].color, PorterDuff.Mode.SRC_IN)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun convertString(str: String): String {
        val listString = str.toList()

        if (listString.size > 1) {
            if (listString.size == 2) {
                listString.addWrapToListString(1)
            } else {
                val wrapIndex = getWrapIndex(listString)
                listString.addWrapToListString(wrapIndex)
            }
        }
        return listString.joinToString(separator = " ")
    }

    private fun getWrapIndex(list: List<String>): Int {
        //Lấy vị trí của từ nằm giữa chuỗi
        val index = list.size / 2

        //Số lương từ trong chuỗi là chẵn thì xuống hàng tại vị trí ở giữa
        return if (index % 2 == 0) index
        else {
            //Chiều dài của hai chuỗi trước và sau từ nằm giữa, so sánh chiều dài nào nhỏ hơn sẽ cộng thêm từ ở giữa vào và xuống hàng
            var lengthOfLine1 = 0
            var lengthOfLine2 = 0

            for (i in 0 until index) lengthOfLine1 += list[i].length
            for (i in (index + 1) until list.size) lengthOfLine2 += list[i].length

            if (lengthOfLine1 <= lengthOfLine2) index + 1
            else index
        }
    }

}

class KeywordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val keywordTextView = view.tvName
    val keywordLinearLayour = view.llKeyword
}