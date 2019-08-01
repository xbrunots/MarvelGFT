package br.com.gft.marvel.platform.base

import androidx.appcompat.app.AppCompatActivity
import br.com.gft.marvel.platform.custom.CustomLoadingDialog
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.os.StrictMode
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar


open class BaseActivity : AppCompatActivity() {
    lateinit var customLoadingDialog: CustomLoadingDialog

    fun onShowLoading() {
        if (!::customLoadingDialog.isInitialized) {
            customLoadingDialog = CustomLoadingDialog(this)
        }
        customLoadingDialog.show()
    }

    fun onHideLoading() {
        if (customLoadingDialog.isShowing) {
            customLoadingDialog.dismiss()
        }
    }

    fun doLoading(show: Boolean) {
        if (show) onShowLoading() else onHideLoading()
    }

    override fun onStart() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onStart()
    }

    fun onError(v: View, error: String) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show()
    }
}