package br.com.gft.marvel

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MarvelApplication : Application() {
    companion object {
        lateinit var mApplication: Application

        private fun getApplication(): Application {
            return mApplication
        }

        fun getContext(): Context {
            return getApplication().applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}