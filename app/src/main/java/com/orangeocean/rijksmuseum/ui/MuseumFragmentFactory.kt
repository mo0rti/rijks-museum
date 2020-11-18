package com.orangeocean.rijksmuseum.ui

import androidx.fragment.app.FragmentFactory
import com.orangeocean.rijksmuseum.ui.artcollection.ArtCollectionFragment
import com.orangeocean.rijksmuseum.ui.artobject.ArtObjectFragment
import javax.inject.Inject

class MuseumFragmentFactory
@Inject
constructor(): FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String) =
        when(className){
            ArtCollectionFragment::class.java.name -> {
                val fragment = ArtCollectionFragment()
                fragment
            }
            ArtObjectFragment::class.java.name -> {
                val fragment = ArtObjectFragment()
                fragment
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
}