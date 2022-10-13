package com.krak.krakowautobusy.ui.vehiclestop

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.networkttss.Depart
import com.krak.krakowautobusy.ui.map.vehicledata.StopData
import java.util.*
import kotlin.collections.ArrayList

class AdapterListViewDepatures (data: ArrayList<Depart>, context: Context) :
    ArrayAdapter<Depart>(context, R.layout.one_time_stop_on_vehiclestop,
        data as ArrayList<Depart>
    ), View.OnClickListener {
    private var dataSet: ArrayList<Depart>
    var mContext: Context

    private var listView: ViewGroup?=null

    override fun getCount(): Int {
        return dataSet.size
    }


    fun changeDataset(Linedata:ArrayList<Depart>){
        dataSet=Linedata
        notifyDataSetChanged()
        notifyDataSetInvalidated()
    }


    private class ViewHolder {
        var lineNumber: TextView?=null
        var nameLineDirection: TextView?=null
        var timeDeparture: TextView?=null
        var iconStatus: ImageView?=null
        var lineNumberBox:LinearLayout?=null


    }

    override fun onClick(v: View) {
    }

    private var lastPosition = -1


    init {
        dataSet = data
        mContext = context
    }


    override fun getItem(position: Int): Depart {
        return dataSet[position]
    }



    private fun fillViewHolderReferenceView(viewHolder: ViewHolder, view: View){

        viewHolder.lineNumber = view.findViewById(R.id.lineNumber)
        viewHolder.nameLineDirection=view.findViewById(R.id.nameLineDirection)
        viewHolder.timeDeparture=view.findViewById(R.id.timeDeparture)
        viewHolder.iconStatus=view.findViewById(R.id.statusicon)
        viewHolder.lineNumberBox=view.findViewById(R.id.lineNumberBox)

    }







    private fun fillViewData(viewHolder: ViewHolder, dataModel: Depart):ViewHolder{

        viewHolder.lineNumber!!.text = dataModel.patternText

        if(dataModel.patternText.length<3){
            viewHolder.lineNumberBox!!.setBackgroundResource(R.drawable.round_rect_shape_train)
        }else{
            viewHolder.lineNumberBox!!.setBackgroundResource(R.drawable.round_rect_shape_bus)
        }

        if(dataModel.actualTime!=null){
            viewHolder.timeDeparture!!.text = dataModel.plannedTime+" ("+dataModel.actualTime+")"
        }else {
            viewHolder.timeDeparture!!.text = dataModel.plannedTime
        }
        viewHolder.nameLineDirection!!.text=dataModel.direction

        if(dataModel.plannedTime!=dataModel.actualTime && (dataModel.actualTime!=null)){
          viewHolder.iconStatus!!.setBackgroundResource(R.drawable.red_circle)
      }else{
          viewHolder.iconStatus!!.setBackgroundResource(R.drawable.green_circle)
      }


        return viewHolder


    }




    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        listView=parent
        var convertView: View? = convertView

        val dataModel: Depart = getItem(position)

        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.one_time_stop_on_vehiclestop, parent, false)
            fillViewHolderReferenceView(viewHolder,convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder

        }


        lastPosition = position

        fillViewData(viewHolder,dataModel)



        return convertView!!
    }
}

