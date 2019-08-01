package br.com.gft.marvel.platform.extension

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.core.animation.doOnEnd


fun View.animatePulseView(endAnimation: (animator: Animator) -> Unit): ObjectAnimator {
    val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat("scaleX", 1.4f),
        PropertyValuesHolder.ofFloat("scaleY", 1.4f)
    )
    scaleDown.duration = 300
    scaleDown.repeatCount = 4
    scaleDown.repeatMode = ObjectAnimator.REVERSE
    scaleDown.start()
    scaleDown.doOnEnd {
        endAnimation(it)
    }
    return scaleDown
}

fun View.animateZoomIn(): ObjectAnimator {
    val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat("scaleX", 1.2f),
        PropertyValuesHolder.ofFloat("scaleY", 1.2f)
    )
    scaleDown.duration = 350
    scaleDown.start()
    return scaleDown
}

