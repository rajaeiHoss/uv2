package com.airbnb.lottie.model.animatable

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
import com.airbnb.lottie.value.Keyframe

interface AnimatableValue<K, A> {
    fun createAnimation(): BaseKeyframeAnimation<K, A>

    fun getKeyframes(): List<Keyframe<K>>

    fun isStatic(): Boolean
}
