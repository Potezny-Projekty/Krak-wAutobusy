package com.krak.krakowautobusy.ui.detailsline

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.color
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.ui.map.vehicledata.StatusData
import java.util.*
import kotlin.collections.ArrayList


class AdapterTimeTableListView (data: ArrayList<StatusData>, context: Context) :
    ArrayAdapter<StatusData>(context, R.layout.search_result_field_bus,
        data
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




    private fun fillViewData(viewHolder: ViewHolder, dataModel: StatusData): ViewHolder {

        val maxCharactersStopName=30
        val statusPredictedName="PREDICTED"
        val statusDepartName="DEPARTED"
        val minutesInHour=60

        viewHolder.sequenceNumber!!.text="%-3s".format( dataModel.stop_seq_num )
        viewHolder.nameBusStop!!.text=dataModel.stop.name
        if(dataModel.stop.name.length>maxCharactersStopName){
            viewHolder.nameBusStop!!.text=dataModel.stop.name.substring(0,30)+"..."
        }
        viewHolder.timeBusStop!!.text=dataModel.actualTime

        when (dataModel.status) {
            statusPredictedName -> {
                viewHolder.iconStatus!!.setBackgroundResource(R.drawable.green_circle)

                val hours= (Calendar.getInstance().time.hours)- viewHolder.timeBusStop!!.text.split(":")[0].toInt()
                val minutes=  viewHolder.timeBusStop!!.text.split(":")[1].toInt()-Calendar.getInstance().time.minutes
                var diff=0
                if(hours!=0) {
                    diff = (hours * minutesInHour) - minutes
                    diff = kotlin.math.abs(diff)
                }else{
                    diff=minutes
                }

                val text = SpannableStringBuilder()
                    .color(Color.GRAY) { append("%-6s".format("(+$diff) ")) }.color(Color.BLACK){append(viewHolder.timeBusStop!!.text)}

                viewHolder.timeBusStop!!.text=text
            }
            statusDepartName -> {
                viewHolder.iconStatus!!.setBackgroundResource(R.drawable.red_circle)

            }
            else -> {
                viewHolder.iconStatus!!.setBackgroundResource(R.drawable.yellowcircle)
            }
        }


    return viewHolder


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



        return convertView!!
    }
}

