package br.com.gft.marvel.feature.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.gft.marvel.R
import br.com.gft.marvel.feature.home.presentation.HomeActivity
import br.com.gft.marvel.platform.extension.adjustTransucent


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        adjustTransucent()
        Handler().postDelayed({
            HomeActivity.newInstance(this)
            finish()
        }, 500L)
    }
}
