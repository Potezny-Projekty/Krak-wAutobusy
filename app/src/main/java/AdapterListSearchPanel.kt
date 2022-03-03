
package com.example.krakowautobusy
import LineBusData


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class AdapterListSearchPanel(data: ArrayList<LineBusData>, context: Context) :
    ArrayAdapter<LineBusData?>(context, com.example.krakowautobusy.R.layout.search_result_field_bus,
        data as List<LineBusData?>
    ), View.OnClickListener {
    private val dataSet: ArrayList<LineBusData>
    var mContext: Context


    private class ViewHolder {
        var lineNumber: TextView?=null
        var startBusStation:TextView?=null
        var stopBusStation:TextView?=null
        var lineNumberBox:LinearLayout?=null
        var isFavouriteIcon:ImageView?=null
        var idLine:Int?=null

    }

    override fun onClick(v: View) {

    }

    private var lastPosition = -1

    init {
        dataSet = data
        mContext = context
    }



    private fun fillViewHolderReferenceView(viewHolder: ViewHolder, view:View){

        viewHolder.lineNumber = view.findViewById(R.id.lineNumber)
        viewHolder.isFavouriteIcon=view.findViewById(R.id.heartIcon)
        viewHolder.lineNumberBox=view.findViewById(R.id.lineNumberBox)
        viewHolder.startBusStation=view.findViewById(R.id.firstBusStopTextField)
        viewHolder.stopBusStation=view.findViewById(R.id.lastBusStopTextField)
    }


    private fun fillViewData(viewHolder: ViewHolder,dataModel:LineBusData){
        viewHolder.lineNumber!!.text= dataModel.numberLine.toString()
        if(dataModel.isFavourite){
            viewHolder.isFavouriteIcon!!.setImageResource(com.example.krakowautobusy.R.drawable.red_heart_icon)
        }else{
            viewHolder.isFavouriteIcon!!.setImageResource(com.example.krakowautobusy.R.drawable.ic_gray_hert_icon)
        }


        if(dataModel.isBus==Vehicle.BUS){
            viewHolder.lineNumberBox!!.setBackgroundResource(com.example.krakowautobusy.R.drawable.round_rect_shape_bus)
        }else{
            viewHolder.lineNumberBox!!.setBackgroundResource(com.example.krakowautobusy.R.drawable.round_rect_shape_train)
        }



        viewHolder.startBusStation!!.text= dataModel.startBusStop
        viewHolder.stopBusStation!!.text= dataModel.endBusStop
        viewHolder.idLine=dataModel.numberLine
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var convertView: View? = convertView
        val dataModel: LineBusData? = getItem(position)

        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate( R.layout.search_result_field_bus, parent, false)
            fillViewHolderReferenceView(viewHolder,convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder

        }



        lastPosition = position

        fillViewData(viewHolder,dataModel!!)


        return convertView!!
    }
}