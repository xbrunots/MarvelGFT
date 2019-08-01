package br.com.gft.marvel.platform.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity


class ShareUtils {
    companion object {
        fun share(activity: AppCompatActivity, text: String) {
            val intent2 = Intent()
            intent2.action = Intent.ACTION_SEND
            intent2.type = "text/plain"
            intent2.putExtra(Intent.EXTRA_TEXT, text)
            activity.startActivity(Intent.createChooser(intent2, "Compartilhar"))
        }
    }
}