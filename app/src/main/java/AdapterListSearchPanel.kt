import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar



class AdapterListSearchPanel(data: ArrayList<LineBusData>, context: Context) :
    ArrayAdapter<LineBusData?>(context, com.example.krakowautobusy.R.layout.search_result_field_bus,
        data as List<LineBusData?>
    ), View.OnClickListener {
    private val dataSet: ArrayList<LineBusData>
    var mContext: Context

    // View lookup cache
    private class ViewHolder {
        var lineNumber: TextView?=null
        var startBusStation:TextView?=null
        var stopBusStation:TextView?=null
        var lineNumberBox:LinearLayout?=null
        var isFavouriteIcon:ImageView?=null



    }

    override fun onClick(v: View) {
        val position = v.getTag() as Int
        val `object`: Any? = getItem(position)
        val dataModel: LineBusData? = `object` as LineBusData?
      //  when (v.getId()) {
       //     R.id.item_info -> Snackbar.make(
        //        v,
       //         "Release date " + dataModel.getFeature(),
        //        Snackbar.LENGTH_LONG
       //     )
       //        .setAction("No action", null).show()
       // }
    }

    private var lastPosition = -1

    init {
        dataSet = data
        mContext = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the data item for this position
        var convertView: View? = convertView
        val dataModel: LineBusData? = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        val viewHolder: ViewHolder // view lookup cache stored in tag
        val result: View?
        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate( com.example.krakowautobusy.R.layout.search_result_field_bus, parent, false)
            viewHolder.lineNumber = convertView.findViewById(com.example.krakowautobusy.R.id.lineNumber) as TextView
            viewHolder.isFavouriteIcon=convertView.findViewById(com.example.krakowautobusy.R.id.heartIcon) as ImageView
            viewHolder.lineNumberBox=convertView.findViewById(com.example.krakowautobusy.R.id.lineNumberBox) as LinearLayout
            viewHolder.startBusStation=convertView.findViewById(com.example.krakowautobusy.R.id.firstBusStopTextField) as TextView
            viewHolder.stopBusStation=convertView.findViewById(com.example.krakowautobusy.R.id.lastBusStopTextField) as TextView
            //  viewHolder.txtType = convertView.findViewById(R.id.type)
         //   viewHolder.txtVersion = convertView.findViewById(R.id.version_number)
        //    viewHolder.info = convertView.findViewById(R.id.item_info) as ImageView
            result = convertView
            convertView.setTag(viewHolder)
        } else {
            viewHolder = convertView.getTag() as ViewHolder
            result = convertView
        }
     //   val animation: Animation = AnimationUtils.loadAnimation(
      //      mContext,
      //      if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top
      //  )
      //  result.startAnimation(animation)
        lastPosition = position
        viewHolder.lineNumber!!.text=dataModel!!.numberLine.toString()
        if(dataModel!!.isFavourite){
            viewHolder.isFavouriteIcon!!.setImageResource(com.example.krakowautobusy.R.drawable.red_heart_icon)
        }else{
            viewHolder.isFavouriteIcon!!.setImageResource(com.example.krakowautobusy.R.drawable.ic_gray_hert_icon)
        }


        if(dataModel!!.isBus==Vehicle.BUS){
            viewHolder.lineNumberBox!!.setBackgroundResource(com.example.krakowautobusy.R.drawable.round_rect_shape_bus)
        }else{
            viewHolder.lineNumberBox!!.setBackgroundResource(com.example.krakowautobusy.R.drawable.round_rect_shape_train)
        }



        viewHolder.startBusStation!!.text=dataModel!!.startBusStop
        viewHolder.stopBusStation!!.text=dataModel!!.endBusStop


      //  viewHolder.txtType.setText(dataModel.getType())
      //  viewHolder.txtVersion.setText(dataModel.getVersion_number())
      //  viewHolder.info.setOnClickListener(this)
       // viewHolder.info.setTag(position)
        // Return the completed view to render on screen
        return convertView!!
    }
}