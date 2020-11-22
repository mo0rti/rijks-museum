package com.orangeocean.rijksmuseum.ui.artcollection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.ui.artobject.ArtObjectFragment
import kotlinx.android.synthetic.main.art_collection_row_item.view.*

class ArtCollectionRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ArtObject> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.art_collection_row_item, parent, false)
        return ArtCollectionVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ArtCollectionVH -> {
                holder.bind(item)
                holder.itemView.setOnClickListener{
                    val bundle = bundleOf(ArtObjectFragment.ARG_ART_OBJECT to item)
                    holder.itemView
                        .findNavController()
                        .navigate(R.id.action_navigation_art_collection_to_artObjectFragment, bundle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setListItem(items: List<ArtObject>) {
        this.items = items
    }

    class ArtCollectionVH constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.image_thumbnail
        private val artTitle: TextView = itemView.text_art_title
        private val artArtist: TextView = itemView.text_art_artist
        private val artObjectId: TextView = itemView.text_art_object_id

        fun bind(artObject: ArtObject) {
            artTitle.text = artObject.title
            artArtist.text = artObject.artistName
            artObjectId.text = artObject.id

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(artObject.thumbnail)
                .into(thumbnail)
        }
    }
}