
package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.*
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.LineData
import com.example.krakowautobusy.database.VehicleType


typealias FunWithoutParamToVoid = () -> Unit
class AdapterListSearchPanel(data: ArrayList<LineData>, context: Context) :
    ArrayAdapter<LineData>(context, R.layout.search_result_field_bus,
    data as ArrayList<LineData>
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
        var iconVehicleStopViaRoute:ImageView?=null

    }

    override fun onClick(v: View) {

    }

    private var lastPosition = -1
    private  var  funRefresh: FunWithoutParamToVoid? = null
    private  var  funRefresh2: FunWithoutParamToVoid? = null

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
    }


    private fun ifUserWriteNumberShowMiddleVehicleStopIfNotHideMiddleField(vehicleStopViaPotential:String, viewHolder: ViewHolder):ViewHolder{
        if(vehicleStopViaPotential.isNotEmpty()){
            viewHolder.busStopViaRoute?.visibility =View.VISIBLE
            viewHolder.iconVehicleStopViaRoute?.visibility =View.VISIBLE
            viewHolder.busStopViaRoute!!.text =vehicleStopViaPotential
        }else{
            viewHolder.busStopViaRoute?.visibility =View.GONE
            viewHolder.iconVehicleStopViaRoute?.visibility =View.GONE
        }
        return viewHolder
    }


    private fun findIndexClickIcon(numberLine:Int,isFavourite:Boolean){
        for(i in 0 until dataSet.size){
            if(dataSet[i].numberLine==numberLine.toLong()){
                Log.e("kosmos",i.toString()+"")

            var elem=    listView?.let { getView(i,null, it) }

                if (elem != null) {
                    Log.e("kosmos",i.toString()+"ANIMUJE")
                 var a=   elem.findViewById(R.id.heartIcon) as ImageView

                    var aa=elem.findViewById(R.id.lineNumber) as TextView?
                    aa?.text="KURWA"
                // Log.e("kosmos",a.text.toString()+" :D")
                if(isFavourite){
                        a!!.animate()
                            .scaleX(1.2f).setDuration(400).start()

                        a!!.animate()
                            .scaleY(1.2f).setDuration(400).withEndAction {
                                a.animate().scaleX(1.0f).setDuration(400).start()
                                a.animate().scaleY(1.0f).setDuration(400).withEndAction {
                                    funRefresh?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                                    funRefresh2?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                                }
                            }
                    }else{

                        a!!.animate()
                            .scaleX(0.8f).setDuration(400).start()

                        a!!.animate()
                            .scaleY(0.8f).setDuration(400).withEndAction {
                                a.animate().scaleX(1.0f).setDuration(400).start()
                                a.animate().scaleY(1.0f).setDuration(400).withEndAction {
                                    funRefresh?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                                    funRefresh2?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                                }
                            }


                    }


                }

            }

        }
    }

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



     return   ifUserWriteNumberShowMiddleVehicleStopIfNotHideMiddleField(dataModel.busStopViaRoute,viewHolder);





    }


 public fun setFunRefresh2(x:FunWithoutParamToVoid){
     funRefresh2=x
 }
    public fun setRefresh(x:FunWithoutParamToVoid){
        funRefresh=x
    }
    private fun addOnClickListenerToFavoriteIcon(viewHolder:ViewHolder,lineData: LineData){
        viewHolder.isFavouriteIcon?.setOnClickListener {
Log.e("kurwa","JEGO MAĆ 3")

            lineData.isFavourite=!lineData.isFavourite
           var x=   fillViewData(viewHolder,lineData)





            if(lineData.isFavourite) {
                findIndexClickIcon(lineData.numberLine.toInt(),true)
                Api.getApi().addLineToFavourite(lineData.numberLine.toInt())


                it!!.animate()
                    .scaleX(1.2f).setDuration(400).start()

                it!!.animate()
                    .scaleY(1.2f).setDuration(400).withEndAction {
                        it.animate().scaleX(1.0f).setDuration(400).start()
                        it.animate().scaleY(1.0f).setDuration(400).withEndAction {
                            funRefresh?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                            funRefresh2?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                        }
                    }


            }else{
                findIndexClickIcon(lineData.numberLine.toInt(),false)
                Api.getApi().removeLinesFromFavourites(lineData.numberLine.toInt())





                it!!.animate()
                    .scaleX(0.8f).setDuration(400).start()

                it!!.animate()
                    .scaleY(0.8f).setDuration(400).withEndAction {
                        it.animate().scaleX(1.0f).setDuration(400).start()
                        it.animate().scaleY(1.0f).setDuration(400).withEndAction {
                            funRefresh?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                            funRefresh2?.let { it1 -> it1(); Log.e("Kurwa", "wołam") }
                        }
                    }
            }
            Log.e("kurwa","...KURWA")


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

