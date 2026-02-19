package com.amo.demo.wheelview

class ArrayWheelAdapter<T> : WheelAdapter {
    private val items: Array<T>
    private val length: Int

    constructor(items: Array<T>, length: Int) {
        this.items = items
        this.length = length
    }

    constructor(items: Array<T>) : this(items, DEFAULT_LENGTH)

    override fun getItem(i: Int): String? {
        if (i < 0) {
            return null
        }
        if (i < items.size) {
            return items[i].toString()
        }
        return null
    }

    override fun getItemsCount(): Int {
        return items.size
    }

    override fun getMaximumLength(): Int {
        return length
    }

    companion object {
        const val DEFAULT_LENGTH: Int = -1
    }
}
