package com.krak.krakowautobusy.ui.map

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.color
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.ui.map.vehicledata.StatusData
import com.krak.krakowautobusy.ui.map.vehicledata.StopData
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList


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
        viewHolder.sequenceNumber!!.text="%-3s".format( dataModel.stop_seq_num .toString())
        viewHolder.nameBusStop!!.text=dataModel.stop.name
        if(dataModel.stop.name.length>30){
            viewHolder.nameBusStop!!.text=dataModel.stop.name.substring(0,30)+"..."
        }
        viewHolder.timeBusStop!!.text=dataModel.actualTime
        Log.e("bananek","|"+dataModel.status+"|")
        if(dataModel.status .equals ("PREDICTED")){
            viewHolder.iconStatus!!.setBackgroundResource(R.drawable.green_circle)
            var x=(Html.fromHtml("<font color=orange>(+" + 7 + ")</font>"+   viewHolder.timeBusStop!!.text));

           // val actualLocalTime=LocalDateTime.now()
            //val localTime=LocalDateTime.parse(viewHolder.timeBusStop!!.text)
           // Duration.between(actualLocalTime, localTime).toMillis();

           val hours= (Calendar.getInstance().getTime().hours)- viewHolder.timeBusStop!!.text.split(":")[0].toInt()
           val minutes=  viewHolder.timeBusStop!!.text.split(":")[1].toInt()-Calendar.getInstance().getTime().minutes

            Log.e("czas",Calendar.getInstance().getTime().hours.toString()+" / "+Calendar.getInstance().getTime().minutes.toString())
            var diff=0
          if(hours!=0) {
              diff = (hours * 60) - minutes
              diff = abs(diff)
          }else{
               diff=minutes
          }


            //   val localDateTime = LocalDateTime.parse(viewHolder.timeBusStop!!.text.toString())


            val phoneCodeColor = ContextCompat.getColor(context, R.color.clear_btn_color)
            val text = SpannableStringBuilder()
                .color(Color.GRAY) { append("%-6s".format("(+"+(diff)+") ")) }.color(Color.BLACK){append(viewHolder.timeBusStop!!.text)}




            viewHolder.timeBusStop!!.text=text
        }else if(dataModel.status .equals( "DEPARTED")){
            viewHolder.iconStatus!!.setBackgroundResource(R.drawable.red_circle)
            Log.e("bananek","|KURWA"+"|")
        }else{
            viewHolder.iconStatus!!.setBackgroundResource(R.drawable.yellowcircle)
        }






return viewHolder


    }



    private fun addOnClickListenerToFavoriteIcon(viewHolder:ViewHolder,lineData: StopData){

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

   //     if (convertView != null) return convertView;
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

