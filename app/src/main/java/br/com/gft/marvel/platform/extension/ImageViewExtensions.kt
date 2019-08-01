package br.com.gft.marvel.platform.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.jetbrains.annotations.Nullable


fun ImageView.loadWithGlide(url: String) {
    val imageView = this
    Glide.with(context).load(url).into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(@NonNull resource: Drawable, @Nullable transition: Transition<in Drawable>?) {
            imageView.setImageDrawable(resource)
        }

        override fun onLoadCleared(@Nullable placeholder: Drawable?) {}

    })
}