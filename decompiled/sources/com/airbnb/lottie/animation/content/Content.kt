package com.airbnb.lottie.animation.content

interface Content {
    fun getName(): String

    @JvmSuppressWildcards
    fun setContents(contentsBefore: List<Content>, contentsAfter: List<Content>)
}
