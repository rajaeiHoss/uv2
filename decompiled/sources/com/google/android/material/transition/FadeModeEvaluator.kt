package com.google.android.material.transition

internal interface FadeModeEvaluator
{
    fun evaluate(f: Float, f2: Float, f3: Float, f4: Float): FadeModeResult
}
