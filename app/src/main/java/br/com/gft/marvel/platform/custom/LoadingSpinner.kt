package br.com.gft.marvel.platform.custom

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import br.com.gft.marvel.R
import org.jetbrains.annotations.Nullable


class LoadingSpinner : ImageView {

    constructor(context: Context) : super(context, null) {}

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        setImageResource(R.drawable.ic_shield)
        startAnimation()
    }

    private fun startAnimation() {
        clearAnimation()

        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 800L
        rotate.repeatCount = Animation.INFINITE
        startAnimation(rotate)
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)

        if (visibility == View.VISIBLE) {
            startAnimation()
        } else {
            clearAnimation()
        }
    }
}