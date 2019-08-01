package br.com.gft.marvel.feature.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import br.com.gft.marvel.R
import br.com.gft.marvel.feature.home.data.service.model.CharacterItem
import br.com.gft.marvel.platform.extension.animatePulseView
import br.com.gft.marvel.platform.extension.loadWithGlide
import br.com.gft.marvel.platform.extension.setTintColor
import br.com.gft.marvel.platform.util.IntentUtils.Companion.openWebPage
import br.com.gft.marvel.platform.util.ShareUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_bottom_detail.view.*

class DetailBottomSheet : BottomSheetDialogFragment() {

    private lateinit var characterItem: CharacterItem
    private lateinit var mActivity: AppCompatActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bindView(inflater.inflate(R.layout.layout_bottom_detail, container, false))
    }

    private fun bindView(view: View) = view.apply {
        this.ivPicture.loadWithGlide(characterItem.thumbnail.path.plus("." + characterItem.thumbnail.extension))
        this.tvTitle.text = characterItem.name
        this.tvDescription.text = characterItem.description
        //shareItem
        this.ibShare.setOnClickListener {
            ShareUtils.share(mActivity, characterItem.resourceURI)
        }
        //close
        this.ibClose.setOnClickListener {
            dismiss()
        }
        //like
        this.ibLike.setOnClickListener {
            it.animatePulseView {
                this.ibLike.invertLikeColors()
            }
        }
        //go Detal Page
        this.btnDetail.setOnClickListener {
            openWebPage(mActivity, characterItem.urls!![0].url!!)
        }
        //go comiclink Page
        this.btnComicLink.setOnClickListener {
            openWebPage(mActivity, characterItem.urls!![2].url!!)
        }
        //go wiki Page
        this.btnWiki.setOnClickListener {
            openWebPage(mActivity, characterItem.urls!![1].url!!)
        }
    }

    private fun ImageButton.invertLikeColors() {
        if (this.tag == R.color.colorLiked) {
            this.tag = R.color.colorWhite
            this.ibLike.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            this.setTintColor(R.color.colorWhite)
        } else {
            this.ibLike.setImageResource(R.drawable.ic_favorite_black_24dp)
            this.tag = R.color.colorLiked
            this.setTintColor(R.color.colorLiked)
        }
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        dismiss()
    }

    companion object {
        fun newInstance(activity: AppCompatActivity, tag: String, _characterItem: CharacterItem) {
            DetailBottomSheet().apply {
                characterItem = _characterItem
                mActivity = activity
            }.show(activity.supportFragmentManager, tag)
        }
    }
}