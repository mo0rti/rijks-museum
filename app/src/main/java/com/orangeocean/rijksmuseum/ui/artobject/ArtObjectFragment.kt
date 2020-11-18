package com.orangeocean.rijksmuseum.ui.artobject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_art_object.view.*

@AndroidEntryPoint
class ArtObjectFragment : Fragment() {
    companion object {
        const val ARG_ART_OBJECT: String = "artObject"
    }

    private lateinit var artObject: ArtObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ART_OBJECT)) {
                artObject = arguments?.getParcelable(ARG_ART_OBJECT)!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_art_object, container, false)

        artObject?.let {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(this@ArtObjectFragment)
                .applyDefaultRequestOptions(requestOptions)
                .load(it.imageUrl)
                .into(root.image_art_object)

            root.text_title.text = it.title
            root.text_description.text = it.artistName
        }

        return root;
    }
}