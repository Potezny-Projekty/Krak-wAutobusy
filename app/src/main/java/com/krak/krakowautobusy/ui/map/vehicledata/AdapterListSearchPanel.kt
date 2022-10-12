
package com.krak.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.LineData
import com.krak.krakowautobusy.database.VehicleType


typealias FunWithoutParamToVoid = () -> Unit
class AdapterListSearchPanel(data: ArrayList<LineData>, context: Context) :
    ArrayAdapter<LineData>(context, R.layout.search_result_field_bus,
    data
    ), View.OnClickListener {
    private var dataSet: ArrayList<LineData>
    var mContext: Context

    private var listView:ViewGroup?=null


    override fun getCount(): Int {
        return dataSet.size
    }


    fun changeDataset(Linedata:ArrayList<LineData>){
        dataSet=Linedata
        notifyDataSetChanged()
        notifyDataSetInvalidated()
    }


    private class ViewHolder {
        var lineNumber: TextView?=null
        var startBusStation:TextView?=null
        var stopBusStation:TextView?=null
        var lineNumberBox:LinearLayout?=null
        var isFavouriteIcon:ImageView?=null
        var busStopViaRoute:TextView?=null
        var idLine:Int?=null
        var circleRed:ImageView?=null
        var circleGreen:ImageView?=null
        var iconVehicleStopViaRoute:ImageView?=null

    }

    override fun onClick(v: View) {

    }

    private var lastPosition = -1
    private  var  funRefreshData: FunWithoutParamToVoid? = null
    private  var  funRefreshDataTwo: FunWithoutParamToVoid? = null

    init {
        dataSet = data
        mContext = context
    }


    override fun getItem(position: Int): LineData {
        return dataSet[position]
    }



    private fun fillViewHolderReferenceView(viewHolder: ViewHolder, view:View){

        viewHolder.lineNumber = view.findViewById(R.id.lineNumber)
        viewHolder.isFavouriteIcon=view.findViewById(R.id.heartIcon)
        viewHolder.lineNumberBox=view.findViewById(R.id.lineNumberBox)
        viewHolder.startBusStation=view.findViewById(R.id.firstBusStopTextField)
        viewHolder.stopBusStation=view.findViewById(R.id.lastBusStopTextField)
        viewHolder.busStopViaRoute=view.findViewById(R.id.busStopViaRoute)
        viewHolder.iconVehicleStopViaRoute=view.findViewById(R.id.iconVehicleStopViaRoute)
        viewHolder.circleGreen=view.findViewById(R.id.circleGreen)
        viewHolder.circleRed=view.findViewById(R.id.circleRed)
    }


    private fun ifUserWriteNumberShowMiddleVehicleStopIfNotHideMiddleField(vehicleStopViaPotential:String, viewHolder: ViewHolder,isShowVS:Boolean):ViewHolder{
        if(vehicleStopViaPotential.isNotEmpty()){
            viewHolder.busStopViaRoute?.visibility =View.VISIBLE
            viewHolder.iconVehicleStopViaRoute?.visibility =View.VISIBLE
            viewHolder.busStopViaRoute!!.text =vehicleStopViaPotential
        }else{
            viewHolder.busStopViaRoute?.visibility =View.GONE
            viewHolder.iconVehicleStopViaRoute?.visibility =View.GONE
        }

        if(isShowVS){
            viewHolder.busStopViaRoute?.visibility =View.VISIBLE
        }
        return viewHolder
    }



    private fun runAnimAddDeleteFromFavourite (isFavourite:Boolean,heartIcon:ImageView){
        val animScaleUpFactor=1.2f
        val animNormalScaleFactor=1.0f
        val animScaleDownFactor=0.8f
        val animDurationInMs=400L

        if(isFavourite){
            heartIcon.animate()
                .scaleX(animScaleUpFactor).setDuration(animDurationInMs).start()

            heartIcon.animate()
                .scaleY(animScaleUpFactor).setDuration(animDurationInMs).withEndAction {
                    heartIcon.animate().scaleX(animNormalScaleFactor).setDuration(animDurationInMs).start()
                    heartIcon.animate().scaleY(animNormalScaleFactor).setDuration(animDurationInMs).withEndAction {
                        funRefreshData?.let { it1 -> it1();  }
                        funRefreshDataTwo?.let { it1 -> it1();  }
                    }
                }
        }else{

            heartIcon.animate()
                .scaleX(animScaleDownFactor).setDuration(animDurationInMs).start()

            heartIcon.animate()
                .scaleY(animScaleDownFactor).setDuration(animDurationInMs).withEndAction {
                    heartIcon.animate().scaleX(animNormalScaleFactor).setDuration(animDurationInMs).start()
                    heartIcon.animate().scaleY(animNormalScaleFactor).setDuration(animDurationInMs).withEndAction {
                        funRefreshData?.let { it1 -> it1();  }
                        funRefreshDataTwo?.let { it1 -> it1();  }
                    }
                }


        }


    }


    private fun findIndexClickIcon(numberLine:Int,isFavourite:Boolean){
        val defaultText=""


        for(i in 0 until dataSet.size){
            if(dataSet[i].numberLine==numberLine.toLong()){

                val view=    listView?.let { getView(i,null, it) }

                if (view != null) {

                 val heartIcon=   view.findViewById(R.id.heartIcon) as ImageView
                 val lineNumberView=view.findViewById(R.id.lineNumber) as TextView?
                 lineNumberView?.text=defaultText

                 runAnimAddDeleteFromFavourite(isFavourite,heartIcon)

            }

        }
    }}

    private fun fillViewData(viewHolder: ViewHolder, dataModel:LineData):ViewHolder{
        viewHolder.lineNumber!!.text= dataModel.numberLine .toString()


        if(dataModel.isFavourite){
            viewHolder.isFavouriteIcon!!.setImageResource(R.drawable.red_heart_icon)
        }else{
            viewHolder.isFavouriteIcon!!.setImageResource(R.drawable.ic_gray_hert_icon)
        }


        if(dataModel.typeVehicle==VehicleType.BUS){
            viewHolder.lineNumberBox!!.setBackgroundResource(R.drawable.round_rect_shape_bus)
        }else{
            viewHolder.lineNumberBox!!.setBackgroundResource(R.drawable.round_rect_shape_train)
        }


        viewHolder.startBusStation!!.text= dataModel.firstStopName
        viewHolder.stopBusStation!!.text= dataModel.lastStopName
        viewHolder.idLine=dataModel.numberLine.toInt()

        var isShowVehicleStop=false

        val scaleDecreaseImage=0.8f
        val scaleNormalImage=1f
        if(dataModel.nameBusStop.isNotEmpty()){

          viewHolder.lineNumberBox!!.setBackgroundResource(R.drawable.ic_bus_stop)
          viewHolder.lineNumberBox!!.scaleX=scaleDecreaseImage
          viewHolder.lineNumberBox!!.scaleY=scaleDecreaseImage
          viewHolder.lineNumber!!.text=""
          viewHolder.busStopViaRoute?.visibility =View.VISIBLE
          viewHolder.busStopViaRoute!!.text=dataModel.nameBusStop
          viewHolder.circleRed!!.visibility=View.GONE
          viewHolder.circleGreen!!.visibility=View.GONE
          isShowVehicleStop=true


      }else{
          viewHolder.circleRed!!.visibility=View.VISIBLE
          viewHolder.circleGreen!!.visibility=View.VISIBLE
          viewHolder.lineNumberBox!!.scaleX=scaleNormalImage
          viewHolder.lineNumberBox!!.scaleY=scaleNormalImage
      }


     return   ifUserWriteNumberShowMiddleVehicleStopIfNotHideMiddleField(dataModel.busStopViaRoute,viewHolder,isShowVehicleStop)


    }



     fun setRefresh(x:FunWithoutParamToVoid){
        funRefreshData=x
    }
    private fun addOnClickListenerToFavoriteIcon(viewHolder:ViewHolder,lineData: LineData){
        val animScaleUpFactor=1.2f
        val animNormalScaleFactor=1.0f
        val animScaleDownFactor=0.8f
        val animDurationInMs=400L
        viewHolder.isFavouriteIcon?.setOnClickListener {

            lineData.isFavourite=!lineData.isFavourite

            if(lineData.isFavourite) {

                if (viewHolder.lineNumberBox!!.background.constantState == context.resources.getDrawable(R.drawable.ic_bus_stop).constantState) {
                    Api.getApi().addVehicleStopToFavorite(lineData.nameBusStop)

                }else {

                    findIndexClickIcon(lineData.numberLine.toInt(), true)
                    Api.getApi().addLineToFavourite(lineData.numberLine.toInt())

                }
                it.animate()
                    .scaleX(animScaleUpFactor).setDuration(animDurationInMs).start()

                it.animate()
                    .scaleY(animScaleUpFactor).setDuration(animDurationInMs).withEndAction {
                        it.animate().scaleX(animNormalScaleFactor).setDuration(animDurationInMs).start()
                        it.animate().scaleY(animNormalScaleFactor).setDuration(animDurationInMs).withEndAction {
                            funRefreshData?.let { it1 -> it1();  }
                            funRefreshDataTwo?.let { it1 -> it1();  }
                        }
                    }


            }else{

                if (viewHolder.lineNumberBox!!.background.constantState == context.resources.getDrawable(R.drawable.ic_bus_stop).constantState) {
                    Api.getApi().removeVehicleStopFromFavourite(lineData.nameBusStop)

                }else {

                    findIndexClickIcon(lineData.numberLine.toInt(), false)
                    Api.getApi().removeLinesFromFavourites(lineData.numberLine.toInt())

                }


                it!!.animate()
                    .scaleX(animScaleDownFactor).setDuration(animDurationInMs).start()

                it.animate()
                    .scaleY(animScaleDownFactor).setDuration(animDurationInMs).withEndAction {
                        it.animate().scaleX(animNormalScaleFactor).setDuration(animDurationInMs).start()
                        it.animate().scaleY(animNormalScaleFactor).setDuration(animDurationInMs).withEndAction {
                            funRefreshData?.let { it1 -> it1();  }
                            funRefreshDataTwo?.let { it1 -> it1();  }
                        }
                    }
            }



        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        listView=parent
        var convertView: View? = convertView

        val dataModel: LineData = getItem(position)

        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.search_result_field_bus, parent, false)
            fillViewHolderReferenceView(viewHolder,convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder

        }


        lastPosition = position

        fillViewData(viewHolder,dataModel)
        addOnClickListenerToFavoriteIcon(viewHolder,dataModel)


        return convertView!!
    }
}

