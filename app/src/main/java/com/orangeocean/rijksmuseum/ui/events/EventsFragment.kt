package com.orangeocean.rijksmuseum.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orangeocean.rijksmuseum.R

class EventsFragment : Fragment() {

  private lateinit var homeViewModel: EventsViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
        ViewModelProvider(this).get(EventsViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_events, container, false)
    val textView: TextView = root.findViewById(R.id.text_home)
    homeViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }
}