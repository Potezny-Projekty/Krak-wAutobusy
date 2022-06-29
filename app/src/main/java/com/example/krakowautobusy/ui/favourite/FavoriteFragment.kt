package com.example.krakowautobusy.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.krakowautobusy.BundleChoiceVehicle
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.LineData

import com.example.krakowautobusy.databinding.FragmentFavoriteBinding
import com.example.krakowautobusy.ui.map.vehicledata.AdapterListSearchPanel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!
    private lateinit var   adapter :AdapterListSearchPanel;

    private fun addAdapterToSearchLineListView(){
        adapter= AdapterListSearchPanel(arrayListOf<LineData>(),requireContext())
        binding.listfavouriteLine.adapter=adapter

        adapter.setRefresh {
            var x=Api.getApi().getAllLine()
            var xx=x.filter( { s -> s.isFavourite }
            )

            Log.e("ilex",""+xx.size )
            adapter.changeDataset(xx as ArrayList<LineData>)
            adapter.notifyDataSetChanged()
        }
    }

    fun addOnClickListenerToLineOnList(){
        binding.listfavouriteLine.setOnItemClickListener { _, view, _, _ ->
            val bundle = bundleOf(
                BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                    view.findViewById<TextView>(R.id.lineNumber).text.toString().trim().toInt(),
                BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                        to view.findViewById<TextView>(R.id.firstBusStopTextField).text.toString(),
                BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                        view.findViewById<TextView>(R.id.lastBusStopTextField).text.toString()

            )

            Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_detailsFragment,bundle);
        }
    }

    private fun setDatasetAdapter(){
        var x=Api.getApi().getAllLine()
       var xx=x.filter( { s -> s.isFavourite }
        )

        Log.e("ilex",""+xx.size )
        adapter.changeDataset(xx as ArrayList<LineData>)
        adapter.notifyDataSetChanged()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addAdapterToSearchLineListView()
        setDatasetAdapter()
        addOnClickListenerToLineOnList()


        binding.textFavourite.paint?.isUnderlineText = true
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}