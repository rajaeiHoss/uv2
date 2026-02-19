package com.amo.demo.wheelview

interface WheelAdapter {
    fun getItem(i: Int): String?

    fun getItemsCount(): Int

    fun getMaximumLength(): Int
}
