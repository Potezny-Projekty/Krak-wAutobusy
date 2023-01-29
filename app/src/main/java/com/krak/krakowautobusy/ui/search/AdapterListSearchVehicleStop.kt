package com.krak.krakowautobusy.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.VehicleStopData

class AdapterListSearchVehicleStop(data: ArrayList<VehicleStopData>, context: Context) :
    ArrayAdapter<VehicleStopData>(context, R.layout.one_favourite_vehiclestop,
        data
    ), View.OnClickListener {
    private var dataSet: ArrayList<VehicleStopData>
    var mContext: Context

    private var listView: ViewGroup?=null


    override fun getCount(): Int {
        return dataSet.size
    }


    fun changeDataset(Linedata:ArrayList<VehicleStopData>){
        dataSet=Linedata
        notifyDataSetChanged()
        notifyDataSetInvalidated()
    }


    private class ViewHolder {
        var lineNumber: TextView?=null
        var isFavourite: ImageView?=null


    }

    override fun onClick(v: View) {

    }

    private var lastPosition = -1
    private  var  funRefresh: FunWithoutParamToVoid? = null


    init {
        dataSet = data
        mContext = context
    }


    override fun getItem(position: Int): VehicleStopData {
        return dataSet[position]
    }



    private fun fillViewHolderReferenceView(viewHolder: ViewHolder, view: View){

        viewHolder.lineNumber = view.findViewById(R.id.nameVehicleStop)
        viewHolder.isFavourite=view.findViewById(R.id.heartIconVehicleStop)


    }




    private fun fillViewData(viewHolder: ViewHolder, dataModel: VehicleStopData): ViewHolder {
        viewHolder.lineNumber!!.text= dataModel.name

        viewHolder.lineNumber!!.tag = dataModel.idStopPoint;

            if(dataModel.isFavourite){
            viewHolder.isFavourite!!.setImageResource(R.drawable.red_heart_icon)


        }else{
            viewHolder.isFavourite!!.setImageResource(R.drawable.ic_gray_hert_icon)


        }


        return viewHolder

    }

  private fun refreshActionAnotherAdapter(){

      funRefresh?.let { it1 -> it1()}
  }

   fun setRefresh(x: FunWithoutParamToVoid){
        funRefresh=x
    }



    private fun addOnClickListenerToFavoriteIcon(viewHolder: ViewHolder, lineData: VehicleStopData){

        val animScaleUpFactor=1.2f
        val animNormalScaleFactor=1.0f
        val animScaleDownFactor=0.8f
        val animDurationInMs=400L



        viewHolder.isFavourite?.setOnClickListener {

            lineData.isFavourite=!lineData.isFavourite


            if(lineData.isFavourite) {
                viewHolder.isFavourite!!.setImageResource(R.drawable.red_heart_icon)


                Api.getApi().addVehicleStopToFavoriteById(lineData.idVehicleStop.toString())

                it!!.animate()
                    .scaleX(animScaleUpFactor).setDuration(animDurationInMs).start()

                it.animate()
                    .scaleY(animScaleUpFactor).setDuration(animDurationInMs).withEndAction {
                        viewHolder.isFavourite!!.animate().scaleX(animNormalScaleFactor).setDuration(animDurationInMs).start()
                        viewHolder.isFavourite!!.animate().scaleY(animNormalScaleFactor).setDuration(animDurationInMs).withEndAction {

                            refreshActionAnotherAdapter()
                        }
                    }


            }else{
                viewHolder.isFavourite!!.setImageResource(R.drawable.ic_gray_hert_icon)


                Api.getApi().removeVehicleStopFromFavouriteById(lineData.idVehicleStop.toString())

                it!!.animate()
                    .scaleX(animScaleDownFactor).setDuration(animDurationInMs).start()

                it.animate()
                    .scaleY(animScaleDownFactor).setDuration(animDurationInMs).withEndAction {
                        it.animate().scaleX(animNormalScaleFactor).setDuration(animDurationInMs).start()
                      it.animate().scaleY(animNormalScaleFactor).setDuration(animDurationInMs).withEndAction {
                          refreshActionAnotherAdapter()
                        }
                    }
            }


        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        listView=parent
        var convertView: View? = convertView

        val dataModel: VehicleStopData = getItem(position)

        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.one_favourite_vehiclestop, parent, false)

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

