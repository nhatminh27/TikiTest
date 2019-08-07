package com.nm.keywordlist

fun String.toList(): MutableList<String>{
    return this.trim().split(" ").toMutableList()
}

fun MutableList<String>.addWrapToListString(index: Int) {
    this.add(index, "\n")
}