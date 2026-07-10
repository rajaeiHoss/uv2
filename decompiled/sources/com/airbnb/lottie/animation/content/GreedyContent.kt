package com.airbnb.lottie.animation.content

internal interface GreedyContent {
    fun absorbContent(contents: MutableListIterator<Content>)
}
