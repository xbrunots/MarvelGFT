package br.com.gft.marvel.platform.util

import android.widget.Toast
import android.content.ActivityNotFoundException
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import br.com.gft.marvel.R


class IntentUtils {
    companion object {
        fun openWebPage(activity: AppCompatActivity, url: String) {
            try {
                val webpage = Uri.parse(url)
                val myIntent = Intent(Intent.ACTION_VIEW, webpage)
                activity.startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.not_browser),
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }

        }
     }
}