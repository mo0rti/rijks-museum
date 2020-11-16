package com.orangeocean.rijksmuseum.ui.artobject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.model.ArtObject

class ArtObjectFragment : Fragment() {
    companion object {
        const val ARG_ART_OBJECT: String = "artObject"
    }

    private lateinit var artObject: ArtObject
    private lateinit var viewModel: ArtObjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artObject = arguments?.getParcelable(ARG_ART_OBJECT)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_art_object, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtObjectViewModel::class.java)
        // TODO: Use the ViewModel
    }
}