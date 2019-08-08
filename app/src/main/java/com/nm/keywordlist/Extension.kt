package com.nm.keywordlist

import android.content.Context
import android.net.ConnectivityManager

fun String.toList(): MutableList<String>{
    return this.trim().split(" ").toMutableList()
}

fun MutableList<String>.addWrapToListString(index: Int) {
    this[index] = this[index-1] +  "\n" + this[index]
    this.removeAt(index - 1)
}

fun Context.checkNetworkConnection(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
}