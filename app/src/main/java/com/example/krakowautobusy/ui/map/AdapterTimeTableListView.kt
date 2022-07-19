package com.example.krakowautobusy.ui.map

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.LineData
import com.example.krakowautobusy.database.VehicleType
import com.example.krakowautobusy.ui.map.vehicledata.AllTableTimeData
import com.example.krakowautobusy.ui.map.vehicledata.StatusData
import com.example.krakowautobusy.ui.map.vehicledata.StopData


class AdapterTimeTableListView (data: ArrayList<StatusData>, context: Context) :
    ArrayAdapter<StatusData>(context, R.layout.search_result_field_bus,
        data as ArrayList<StatusData>
    ), View.OnClickListener {
    private var dataSet: ArrayList<StatusData>
    var mContext: Context

    private var listView: ViewGroup?=null


    override fun getCount(): Int {
        return dataSet.size
    }


    fun changeDataset(Linedata:ArrayList<StatusData>){
        dataSet=Linedata
        notifyDataSetChanged()
        notifyDataSetInvalidated()
    }


    private class ViewHolder {
        var sequenceNumber: TextView?=null
        var nameBusStop:TextView?=null
        var timeBusStop:TextView?=null
        var iconStatus:ImageView?=null



    }

    override fun onClick(v: View) {

    }

    private var lastPosition = -1


    init {
        dataSet = data
        mContext = context
    }


    override fun getItem(position: Int): StatusData {
        return dataSet[position]
    }



    private fun fillViewHolderReferenceView(viewHolder: ViewHolder, view: View){

        viewHolder.sequenceNumber = view.findViewById(R.id.sequenceNumber)
        viewHolder.nameBusStop=view.findViewById(R.id.nameStopBus)
        viewHolder.timeBusStop=view.findViewById(R.id.timeBusStop)
        viewHolder.iconStatus=view.findViewById(R.id.arrival_status_icon)


    }







    private fun fillViewData(viewHolder: ViewHolder, dataModel: StatusData):ViewHolder{
        viewHolder.sequenceNumber!!.text= dataModel.stop_seq_num .toString()
        viewHolder.nameBusStop!!.text=dataModel.stop.name
        viewHolder.timeBusStop!!.text=dataModel.actualTime
        Log.e("bananek","|"+dataModel.status+"|")
        if(dataModel.status .equals ("PREDICTED")){
            viewHolder.iconStatus!!.setBackgroundResource(R.drawable.green_circle)
        }else if(dataModel.status .equals( "DEPARTED")){
            viewHolder.iconStatus!!.setBackgroundResource(R.drawable.red_circle)
            Log.e("bananek","|KURWA"+"|")
        }






return viewHolder


    }



    private fun addOnClickListenerToFavoriteIcon(viewHolder:ViewHolder,lineData: StopData){

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        listView=parent
        var convertView: View? = convertView

        val dataModel: StatusData = getItem(position)

        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.bus_stop_arrival_box, parent, false)
            fillViewHolderReferenceView(viewHolder,convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder

        }



        lastPosition = position

        fillViewData(viewHolder,dataModel)
      //  addOnClickListenerToFavoriteIcon(viewHolder,dataModel)


        return convertView!!
    }
}

