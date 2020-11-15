package com.orangeocean.rijksmuseum.ui.artcollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.orangeocean.rijksmuseum.R
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import kotlinx.android.synthetic.main.fragment_art_collection.*

class ArtCollectionFragment : Fragment() {

    //private lateinit var artCollectionViewModel: ArtCollectionViewModel
    private lateinit var artCollectionRecyclerAdapter: ArtCollectionRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*artCollectionViewModel =
            ViewModelProvider(this).get(ArtCollectionViewModel::class.java)
        */val root = inflater.inflate(R.layout.fragment_art_collection, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        artCollectionViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setData()
    }

    private fun setData() {
        val items = ArrayList<ArtObject>();
        ///items.add(ArtObject("1", "Image 1", "https://lh3.googleusercontent.com/gShVRyvLLbwVB8jeIPghCXgr96wxTHaM4zqfmxIWRsUpMhMn38PwuUU13o1mXQzLMt5HFqX761u8Tgo4L_JG1XLATvw=s0"))
        items.add(ArtObject("1", "Image 1", "https://lh3.googleusercontent.com/MonorlaCKkTjeFrTBQyCb7kfmq8K--aWrThge7MsTItJxO_cw-8WbwgmTm0jTt-qfaFfnwa1qNEnsGYVHMPmCGmb0Pje=s0"))
        items.add(ArtObject("2", "Image 2", "https://lh3.googleusercontent.com/8vtyRTsJdGXuZhnHl5wF6vBgDNInw3q56DFYYr0Rzm1JJYNRl2iSIC30d_erXkTe_Yv8uJq1ZL56zFrXUpTFJrBDcbE=s0"))
        items.add(ArtObject("3", "Image 3", "https://lh3.googleusercontent.com/WKIxue0nAIOYj00nGVoO4DTP9rU2na0qat5eoIuQTf6Fbp4mHHm-wbCes1Oo6K_6IdMl6Z_OCjGW_juCCf_jvQqaKw=s0"))
        items.add(ArtObject("4", "Image 4", "https://lh3.googleusercontent.com/O7ES8hCeygPDvHSob5Yl4bPIRGA58EoCM-ouQYN6CYBw5jlELVqk2tLkHF5C45JJj-5QBqF6cA6zUfS66PUhQamHAw=s0"))
        artCollectionRecyclerAdapter.setListItem(items)
    }

    private fun initRecyclerView() {
        recycler_view_art_collection.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(ArtCollectionRecyclerItemDecoration(resources.getInteger(R.integer.list_item_card_padding)))
            artCollectionRecyclerAdapter = ArtCollectionRecyclerAdapter()
            adapter = artCollectionRecyclerAdapter
        }
    }
}