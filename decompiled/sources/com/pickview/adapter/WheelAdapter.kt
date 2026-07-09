package com.pickview.adapter

interface WheelAdapter<T> {
    fun getItem(index: Int): T?

    fun getItemsCount(): Int

    fun indexOf(item: T?): Int
}
