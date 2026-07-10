package com.streamax.config.fragment

interface SelectInterface {
    @JvmSuppressWildcards
    fun saveSelect(name: String, selectedItems: List<Int>)
}
