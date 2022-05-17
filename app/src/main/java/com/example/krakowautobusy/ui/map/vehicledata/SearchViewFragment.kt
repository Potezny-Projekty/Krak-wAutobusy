package com.example.krakowautobusy.ui.map.vehicledata

import android.animation.*
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.FragmentSearchViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchViewFragment : Fragment() {
    private var _binding: FragmentSearchViewBinding? = null
    private var isShowKeyboard=false
    private  var bottomNavView:BottomNavigationView?=null

    private var showAllOrFavoriteBusOnMap_FAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    private var findMyLocationOnMap_FAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    val ANIM_DURATION_MS=800L

    private lateinit var   adapter :AdapterListSearchPanel;

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchViewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter= AdapterListSearchPanel(arrayListOf<com.example.krakowautobusy.database.LineData>(),requireContext())
        binding.searchList.adapter=adapter
        return root
    }

    private fun allowAccessKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun showListSearchViewAnimation(){
        val view=binding.allSearchPane

        view.pivotX = 0f
        view.pivotY = 0f

        view.animate()
            .scaleY(1f)
            .setInterpolator(AccelerateDecelerateInterpolator()).duration = ANIM_DURATION_MS/2
   /*     searchList!!.visibility = View.VISIBLE

        val anim = ValueAnimator.ofInt(searchList.getMeasuredHeight(), -100)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = searchList.getLayoutParams()
            layoutParams.height = `val`
            searchList.setLayoutParams(layoutParams)
        }
        anim.duration = 800
        anim.start()

*/

     //   searchList!!.visibility = View.VISIBLE
      //  searchList.setAlpha(0.0f);


      //  searchList.animate()
     //       .translationY(searchList.getHeight().toFloat())
     //       .alpha(1.0f)
     //       .start()
    }

    private fun hideListSearchViewAnimation(){
        val view=binding.allSearchPane
        view.pivotX = 0f;
        view.pivotY = 0f

        view.animate()
            .scaleY(0f)
            .setInterpolator(AccelerateDecelerateInterpolator()).duration = ANIM_DURATION_MS/2

/*
        val view=binding.allSearchPane
        view!!.animate()
            .translationY(0f)
            .alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view!!.visibility = View.GONE
                }
            })*/
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAnimIconSearchWhenUserFocusSearchBar()
        actionWhenSearchPanelClickIcon()
        allowAccessKeyboard()
        initialiseAnimation()


        hideListOption()
        addWatchListenerToSearch()
        addCallbackToDeleteIconDeleteText()
        addHandlerToInputText()



    }

    private fun addHandlerToInputText(){
      binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try{
            // binding.searchEditText.text.toString().toInt()

                    val dataModels = ArrayList<LineData>()
                    var xD:ArrayList<com.example.krakowautobusy.database.LineData> =ArrayList<com.example.krakowautobusy.database.LineData>()

                    //rozrzerz to na dodanie obu wyszukiwan
                  //  val xD= Api.getApi().getInfoAboutLinePatternNumber(binding.searchEditText.text.toString().toInt())
               if(binding.searchEditText.text.toString().length>2) {
                    xD = Api.getApi()
                       .getInfoAboutLinePatternAnyVehicleStop(binding.searchEditText.text.toString())
               }

               Log.e("searchV",xD.size.toString()+" xD")

                for (x in xD){
                    Log.e("searchV",x.numberLine.toString())
                }
                    //    val  adapter = AdapterListSearchPanel(xD,requireContext())
adapter.changeDataset(xD)
                   // binding.searchList.adapter = adapter
                    adapter.notifyDataSetChanged()


                }
                catch (exp:Exception){
println(exp.message)
                    Log.e("searchV",exp.message.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }




    private fun addDataToSearchView(){
      val dataModels = ArrayList<LineData>()
   //  val xD= Api.getApi().getInfoAboutLinePatternNumber(5)
   //     val  adapter = AdapterListSearchPanel(xD,requireContext())

      // binding.searchList.adapter = adapter

      dataModels.add(LineData(1,VehicleEnum.BUS,537,"Witkowice","Dworzec Główny Wschód",true))
      dataModels.add(LineData(2,VehicleEnum.BUS,112,"Os,Podwawelskie","Tyniec Kamieniołom",false))
      dataModels.add(LineData(3,VehicleEnum.TRAM,5,"Wzgórze Krzesłowickie","Krowodrza Górka",true))
      dataModels.add(LineData(4,VehicleEnum.TRAM,17,"Czerwone Maki P+R","Dworzec Towarowy",false))


    }




    private fun getAccessToViewFromOtherFragmentOrActivity(){
        if(bottomNavView == null){
            bottomNavView=requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            findMyLocationOnMap_FAB=requireActivity().findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.extended_fab2)
            showAllOrFavoriteBusOnMap_FAB=requireActivity().findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.Map_showAllVehiclesOrFavorite)
        }

    }

    private fun hideApplicationNavBar(){
        getAccessToViewFromOtherFragmentOrActivity()
        bottomNavView?.visibility=View.GONE
        findMyLocationOnMap_FAB?.visibility=View.GONE
        showAllOrFavoriteBusOnMap_FAB?.visibility=View.GONE
    }

    private fun showApplicationNavBar(){
        getAccessToViewFromOtherFragmentOrActivity()
        bottomNavView?.visibility=View.VISIBLE
        findMyLocationOnMap_FAB?.visibility=View.VISIBLE
        showAllOrFavoriteBusOnMap_FAB?.visibility=View.VISIBLE



    }





    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Fragment.showKeyboard() {
        view?.let { activity?.showKeyboard(it) }
    }
    private fun Context.showKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
    }





    private fun changeSearchIconToActiveSearchIcon(){
//        val searchIcon=binding.searchIcon
       // searchIcon.setImageResource(R.drawable.back_icon_green)
    }

    private fun changeSearchIconToNoActiveSearchIcon(){
//        val searchIcon=binding.searchIcon
     //   searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
    }



    private fun initialiseAnimation(){
        prepareRotateIconSearchAnimation()
        prepareResizeIconSearchAnimation()
    }

    private lateinit var rotateAnimation:ObjectAnimator
    private lateinit var rotateAnimationReverse:ObjectAnimator
    private fun prepareRotateIconSearchAnimation(){
        val searchIcon=binding.searchIcon
        val animatorRotateIcon = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, -180f, 0f)
        animatorRotateIcon.duration = ANIM_DURATION_MS
        rotateAnimation=animatorRotateIcon



        val animatorRotateIconReverse = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, 180f, 0f)
        animatorRotateIconReverse.duration = ANIM_DURATION_MS
        rotateAnimationReverse=animatorRotateIconReverse


    }

    private lateinit var scaleXAnimationScaleUo:ObjectAnimator
    private lateinit var scaleYAnimationScaleUp:ObjectAnimator

    private lateinit var scaleXAnimationScaleDown:ObjectAnimator
    private lateinit var scaleYAnimationScaleDown:ObjectAnimator
    private fun prepareResizeIconSearchAnimation(){
        val searchIcon=binding.searchIcon
        val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 0f, 1.5f)
        val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 0f, 1.5f)
        scaleX.duration=ANIM_DURATION_MS
        scaleY.duration=ANIM_DURATION_MS

        scaleXAnimationScaleUo=scaleX
        scaleYAnimationScaleUp=scaleY


        val scaleYDown = ObjectAnimator.ofFloat(searchIcon, "scaleY", 1.5f, 0f)
        val scaleXDown = ObjectAnimator.ofFloat(searchIcon, "scaleX", 1.5f, 0f)
        scaleXDown.duration=ANIM_DURATION_MS
        scaleYDown.duration=ANIM_DURATION_MS
        scaleXAnimationScaleDown=scaleXDown
        scaleYAnimationScaleDown=scaleYDown


    }

    private fun runOpenSearchPanelAnimation(){

        val ALFA_BACGROUND_WHEN_SHOW_SEARCH_VIEW_PERCENTAGE=40

        hideApplicationNavBar()

        binding.root.setBackgroundColor(Color.GRAY)
        binding.root.background.alpha=ALFA_BACGROUND_WHEN_SHOW_SEARCH_VIEW_PERCENTAGE



        rotateAnimation.doOnEnd {

            changeSearchIconToActiveSearchIcon()
            val animationSet=AnimatorSet()
            animationSet.playTogether(scaleXAnimationScaleUo,scaleYAnimationScaleUp)
            animationSet.start()

        }

        val animationSet = AnimatorSet()
        animationSet.playTogether(scaleYAnimationScaleDown, scaleXAnimationScaleDown,rotateAnimation)
        animationSet.start()
    }

    private fun actionWhenSearchPanelClickIcon(){
        val searchIcon=binding.searchIcon


        searchIcon.setOnClickListener {

            if(isShowKeyboard){
            hideKeyboard()
            binding.searchEditText.clearFocus()

          //  changeSearchIconToActiveSearchIcon()

        }else{
            binding.searchEditText.requestFocus()
            showKeyboard()


        }}
    }


    override fun onStart() {
        super.onStart()
        addListenerToKeyboard()

    }

    private var mLastContentHeight = 0
    private val keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
    val KEYBOARD_EXPECT_AREA=100

        try{//to jest kurna zajebiste :D

            val currentContentHeight: Int =
                requireActivity().findViewById<View>(Window.ID_ANDROID_CONTENT).height
            if (mLastContentHeight > currentContentHeight + KEYBOARD_EXPECT_AREA) {
                isShowKeyboard=true
                mLastContentHeight = currentContentHeight
            } else if (currentContentHeight > mLastContentHeight + KEYBOARD_EXPECT_AREA) {
                mLastContentHeight = currentContentHeight
                isShowKeyboard=false



                val editTxt=binding.searchEditText
                if(editTxt !=null){
                    editTxt.clearFocus()
                }

            }

        }catch (e:Exception ){

        }



    }
    private fun addListenerToKeyboard(){
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener);

    }



    private fun hideListOption(){
    hideListSearchViewAnimation()
    //  binding.allSearchPane.visibility=View.GONE
    }

    private fun showListOption(){
      //  binding.allSearchPane.visibility=View.VISIBLE
        showListSearchViewAnimation()
    }


    private fun runCloseSearchPanelAnimation(){
        showApplicationNavBar()
        hideKeyboard()

        binding.searchEditText.clearFocus()
        changeSearchIconToActiveSearchIcon()


        val animationSet = AnimatorSet()
        animationSet.playTogether(scaleYAnimationScaleDown, scaleXAnimationScaleDown)
        animationSet.start()


        scaleYAnimationScaleDown.doOnEnd {

            val animationSetEnd=AnimatorSet()
            animationSetEnd.playTogether(scaleXAnimationScaleUo,scaleYAnimationScaleUp)
            animationSetEnd.start()
            changeSearchIconToNoActiveSearchIcon()
            rotateAnimationReverse.start()
        }

    }



    private fun showDeleteTextIconWhenCharAboveOrHide(){
        val deleteIcon=binding.deleteUserWriteTextIcon
        if(binding.searchEditText.text.toString().length>0){

            deleteIcon.visibility=View.VISIBLE
        }else{
            deleteIcon.visibility=View.INVISIBLE
        }


    }

    private fun addWatchListenerToSearch(){
        val searchEditText=binding.searchEditText
        searchEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                showDeleteTextIconWhenCharAboveOrHide()
            }
        })
    }


    private fun addCallbackToDeleteIconDeleteText(){
        val deleteIcon=binding.deleteUserWriteTextIcon
        val searchEditText=binding.searchEditText
        deleteIcon.setOnClickListener {
            searchEditText.setText(  "")
        }
    }


    private fun addAnimIconSearchWhenUserFocusSearchBar(){

        val searchEditText=binding.searchEditText


        searchEditText.setOnFocusChangeListener { _, hasFocus ->


            if (hasFocus) {

                runOpenSearchPanelAnimation()
                addDataToSearchView()//skasuj to xD
                showListOption()



            } else {

                runCloseSearchPanelAnimation()
                hideListOption()

            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}