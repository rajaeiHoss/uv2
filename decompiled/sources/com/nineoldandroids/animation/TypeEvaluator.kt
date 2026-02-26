package com.nineoldandroids.animation

interface TypeEvaluator<T> {
    fun evaluate(f: Float, t: T, t2: T): T
}
