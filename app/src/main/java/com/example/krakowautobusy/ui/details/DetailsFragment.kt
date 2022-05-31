package com.example.krakowautobusy.ui.details

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BundleChoiceVehicle
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.FragmentDetailsBinding
import com.example.krakowautobusy.ui.map.CreateDetailsMapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.osmdroid.views.MapView


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val DEFAULT_VEHICLE_NUMBER=0;
    private val NUMBER_LINE_TOP_TEXT_FORMAT="Linia numer "//to ze stringów brać trzeba xD Kurwa Mać JA pierdole
    private  var lineNumber:Int=DEFAULT_VEHICLE_NUMBER;
    private var firstVehicleStopName=""
    private var lastVehicleStopName=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

    //    Toast.makeText(context, arguments?.getInt("lineNumber").toString(), Toast.LENGTH_SHORT).show()
        //Toast.makeText(context, "Reason can not be blank", Toast.LENGTH_SHORT).show();
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        uncheckedAllNavMenuOption()
        getDataFromIntent()
        fillViewsDataFromBundle()



//        var fragment = getFragmentManager()?.findFragmentById(R.id.details_view_map) as CreateDetailsMapFragment;
  //      fragment.drawVehicleStopLines(444,"asd");

    }


    fun getDataFromIntent(){
      lineNumber=  arguments?.getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject)!!.toInt()
        firstVehicleStopName=arguments?.getString(BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject).toString()
        lastVehicleStopName=arguments?.getString(BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject).toString()
    }


    fun fillViewsDataFromBundle(){
        binding.lineNumberTop.text= "$NUMBER_LINE_TOP_TEXT_FORMAT$lineNumber"

      //  var view=binding.details. as LinearLayout;
           binding.details.lineNumberDetailed.text="$lineNumber"
           binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
           binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"
     //   binding.infoAboutLineView.view.findViewById<TextView>(R.id.currentlyFollowing_first_bus_stop_data).text="$firstVehicleStopName"
       // binding.infoAboutLineView.view.findViewById<TextView>(R.id.currentlyFollowing_last_bus_stop_data).text="$lastVehicleStopName"


    }

    fun uncheckedAllNavMenuOption() {
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.menu.setGroupCheckable(0, true, false)
        for (i in 0 until navView.menu.size()) {
            navView.menu.getItem(i).isChecked = false
        }
        navView.menu.setGroupCheckable(0, true, true)
    }
}