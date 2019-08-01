package br.com.gft.marvel.feature.home.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gft.marvel.R
import br.com.gft.marvel.feature.home.data.service.model.CharacterItem
import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import br.com.gft.marvel.feature.home.domain.HomeContract
import br.com.gft.marvel.feature.home.presentation.adapter.CharacterListAdapter
import br.com.gft.marvel.platform.base.BaseActivity
import br.com.gft.marvel.platform.extension.*
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity(), HomeContract.IView {
    lateinit var homePresenter: HomePresenter
    lateinit var adapter: CharacterListAdapter

    companion object {
        fun newInstance(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, HomeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        configTheme()
        homePresenter = HomePresenter(this)
        filterFactory()
        ivConversation.setOnClickListener {
            this.initConversation()
        }
        homePresenter.attachView()
    }

    private fun filterFactory() {
        etFilter.onKeyText {
            homePresenter.filter(it)
        }
    }

    private fun configTheme() {
        adjustTransucent()
        setStatusThemeColor(Color.WHITE, true)
        statusBarDarkIcons()
    }

    override fun invokeAdapter(list: List<CharacterItem>) {
        adapter = CharacterListAdapter(this, list)
        rvCharacter.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun invokeView(items: CharacterResponse) {
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvCharacter.layoutManager = layoutManager
        invokeAdapter(items.data.results)
    }

    override fun showLoading() {
        onShowLoading()
    }

    override fun hideLoading() {
        onHideLoading()
    }

    override fun showError(error: String) {
        onError(rvCharacter, error)
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.detachView()
    }
}
