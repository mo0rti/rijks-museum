package com.orangeocean.rijksmuseum.ui.artobject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orangeocean.rijksmuseum.R

class ArtObjectFragment : Fragment() {

    companion object {
        fun newInstance() = ArtObjectFragment()
    }

    private lateinit var viewModel: ArtObjectViewModel

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