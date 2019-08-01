package br.com.gft.marvel.platform.extension

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.gft.marvel.R
import br.com.gft.marvel.platform.device.DeviceProperties

fun AppCompatActivity.statusBarDarkIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun AppCompatActivity.statusBarLightIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = 0
    }
}

fun AppCompatActivity.adjustTransucent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
    val viewGroup = (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    viewGroup.setPadding(0,  DeviceProperties.getStatusBarHeight(), 0, DeviceProperties.getNavbarHeight())
}

fun AppCompatActivity.initConversation() {
    val uri = Uri.parse(getString(R.string.whatsapp_conversation))
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun AppCompatActivity.setStatusThemeColor(color: Int, isDarkIcons: Boolean = false) {
    try {
        val window = this.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
            window.navigationBarColor = ContextCompat.getColor(this, color)
        }
        if (isDarkIcons) this.statusBarDarkIcons() else this.statusBarLightIcons()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.setNavbarColor(color: Int) {
    try {
        val window = this.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = ContextCompat.getColor(this, color)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}