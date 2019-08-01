package br.com.gft.marvel.feature.home.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.gft.marvel.R
import br.com.gft.marvel.feature.detail.DetailBottomSheet
import br.com.gft.marvel.feature.home.data.service.model.CharacterItem
import br.com.gft.marvel.feature.home.presentation.HomeActivity
import br.com.gft.marvel.platform.extension.loadWithGlide


class CharacterListAdapter(private var context: Context, private val characterList: List<CharacterItem>) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        val characterItem = characterList[position]
        holder.name.text = characterItem.name.toString()
        holder.description.text = characterItem.description.toString()
        holder.thumb.loadWithGlide(characterItem.thumbnail.path.plus("." + characterItem.thumbnail.extension))
        holder.cardItem.setOnClickListener {
            characterItem.getDetails()
        }

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    internal fun getItem(id: Int): CharacterItem {
        return characterList[id]
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.character_layout_item, parent, false)
        return ViewHolder(view)
    }

    fun CharacterItem.getDetails() {
        DetailBottomSheet.newInstance(
            (context as HomeActivity),
            context.getString(R.string.details),
            this
        )
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.findViewById(R.id.tvName)
        internal var description: TextView = itemView.findViewById(R.id.tvDescription)
        internal var thumb: ImageView = itemView.findViewById(R.id.ivBanner)
        internal var cardItem: CardView = itemView.findViewById(R.id.cardMain)
    }
}
