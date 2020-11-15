package com.orangeocean.rijksmuseum.ui.artcollection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import kotlinx.android.synthetic.main.art_collection_row_item.view.*

class ArtCollectionRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ArtObject> = ArrayList()
    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.art_collection_row_item, parent, false)
        navController = parent.findNavController();
        return ArtCollectionVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ArtCollectionVH -> {
                holder.bind(item)
                holder.itemView.setOnClickListener{
                    navController.navigate(R.id.action_navigation_art_collection_to_artObjectFragment)
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
        private val thumbnail: ImageView = itemView.thumbnail
        private val artTitle: TextView = itemView.art_title
        private val artArtist: TextView = itemView.art_artist

        fun bind(artObject: ArtObject) {
            artTitle.text = artObject.title
            artArtist.text = artObject.id

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(artObject.imageUrl)
                .into(thumbnail)
        }
    }
}