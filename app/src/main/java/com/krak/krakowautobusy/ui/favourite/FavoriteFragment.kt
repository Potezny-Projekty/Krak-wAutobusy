package com.krak.krakowautobusy.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.krak.krakowautobusy.BundleChoiceVehicle
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.LineData
import com.krak.krakowautobusy.databinding.FragmentFavoriteBinding
import com.krak.krakowautobusy.ui.map.vehicledata.AdapterListSearchPanel
import com.krak.krakowautobusy.ui.map.vehicledata.SearchViewFragment

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!
    private lateinit var   adapter :AdapterListSearchPanel


    private fun addAdapterToSearchLineListViewAndSetRefresh(){

       binding.listfavouriteLine.adapter=adapter

        adapter.setRefresh {
           setDatasetAdapter()
        }
    }

    private fun addOnClickListenerToLineOnList(){
        binding.listfavouriteLine.setOnItemClickListener { _, view, _, _ ->
            val bundle = bundleOf(
                BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                    view.findViewById<TextView>(R.id.lineNumber).text.toString().trim().toInt(),
                BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                        to view.findViewById<TextView>(R.id.firstBusStopTextField).text.toString(),
                BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                        view.findViewById<TextView>(R.id.lastBusStopTextField).text.toString()

            )

            Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_detailsFragment,bundle)
        }
    }



    private fun hideOrShowNoFavouriteLineText(){

        val searchView= childFragmentManager.findFragmentById(R.id.detailFragment) as SearchViewFragment
        searchView.setRefreshFun {
            setDatasetAdapter()
        }

        searchView.setActionWhenHideKeyboard {

            val allLine=Api.getApi().getAllLine()
            val favouriteLine=allLine.filter { s -> s.isFavourite }
            if(favouriteLine.isEmpty()){
                binding.nolinefavouriteinfo.visibility=View.VISIBLE
            }
        }

        searchView.setActionWhenShowKeyboard {
            binding.nolinefavouriteinfo.visibility=View.GONE
        }

    }

    private fun setDatasetAdapter(){

        val allLines=Api.getApi().getAllLine()
        val favouriteLines=allLines.filter { s -> s.isFavourite }

        if(favouriteLines.isEmpty()){
            binding.nolinefavouriteinfo.visibility=View.VISIBLE
        }else{
            binding.nolinefavouriteinfo.visibility=View.GONE
        }

        adapter.changeDataset(favouriteLines as ArrayList<LineData>)
        adapter.notifyDataSetChanged()

    }



    private fun underLineTextFavourite(){
        binding.textFavourite.paint?.isUnderlineText = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        favoriteViewModel =
            ViewModelProvider(this)[FavoriteViewModel::class.java]

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter= AdapterListSearchPanel(arrayListOf(),requireContext())
        hideOrShowNoFavouriteLineText()
        addAdapterToSearchLineListViewAndSetRefresh()
        setDatasetAdapter()
        addOnClickListenerToLineOnList()
        underLineTextFavourite()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}