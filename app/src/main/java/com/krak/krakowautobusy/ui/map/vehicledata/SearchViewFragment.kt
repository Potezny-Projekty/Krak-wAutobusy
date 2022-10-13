package com.krak.krakowautobusy.ui.map.vehicledata

import android.animation.*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.krak.krakowautobusy.BundleChoiceVehicle
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.LineData
import com.krak.krakowautobusy.databinding.FragmentSearchViewBinding
import com.krak.krakowautobusy.ui.vehiclestop.BundleVehicleStop


class SearchViewFragment : Fragment() {
    private var _binding: FragmentSearchViewBinding? = null
    private var isShowKeyboard=false
    private  var bottomNavView:BottomNavigationView?=null

    private var showAllOrFavoriteBusOnMapFAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    private var findMyLocationOnMapFAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    private var showVehicleStopOrVehiclesFAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null


    private   var refreshAction: RefreshHandler? = null;

    private   var actionShowKeyboard: RefreshHandler? = null;
    private   var actionHidekeyboard: RefreshHandler? = null;



     fun setActionWhenShowKeyboard(action:RefreshHandler){
            actionShowKeyboard=action
    }


     fun setActionWhenHideKeyboard(action:RefreshHandler){
        actionHidekeyboard=action
    }

    private lateinit var   adapter :AdapterListSearchPanel

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addAdapterToSearchLineListView()
        return root
    }


     fun setRefreshFun(zm:RefreshHandler ){
        refreshAction=zm

    }

    private fun addAdapterToSearchLineListView(){
        adapter= AdapterListSearchPanel(arrayListOf(),requireContext())
        binding.searchList.adapter=adapter
    }

    private fun allowAccessKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun showListSearchViewAnimation(){
        AnimationSearchView.showListSearchViewAnimation(binding.allSearchPane)
        actionShowKeyboard?.let { it() }
    }

    private fun hideListSearchViewAnimation(){
        AnimationSearchView.hideListSearchViewAnimation(binding.allSearchPane)
        actionHidekeyboard?.let { it() }
    }



    @SuppressLint("UseCompatLoadingForDrawables")
    fun addOnClickListenerToLineOnList(){
        binding.searchList.setOnItemClickListener { _, view, _, _ ->

            val icon=view.findViewById<LinearLayout>(R.id.lineNumberBox).background
            val defaultValue=""

            if (icon.constantState == requireContext().resources.getDrawable(R.drawable.ic_bus_stop).constantState) {

                val bundle = bundleOf(
                    BundleVehicleStop.ID_VEHICLE_STOP.nameBundle to
                            defaultValue,
                    BundleVehicleStop.NAME_VEHICLE_STOP.nameBundle to
                            view.findViewById<TextView>(R.id.busStopViaRoute).text,
                    BundleVehicleStop.ID_STOP_POINT.nameBundle to
                            defaultValue

                )

                Navigation.findNavController(view).navigate(R.id.actionnavigatedetailesstop, bundle)


            }else{


            val bundle = bundleOf(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                    view.findViewById<TextView>(R.id.lineNumber).text.toString().trim().toInt(),
                BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
            to view.findViewById<TextView>(R.id.firstBusStopTextField).text.toString(),
                BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                        view.findViewById<TextView>(R.id.lastBusStopTextField).text.toString()

                )

            Navigation.findNavController(view).navigate(R.id.action_navigation_map_to_detailsFragment,bundle)
        }}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAnimIconSearchWhenUserFocusSearchBar()
        actionWhenSearchPanelClickIcon()
        allowAccessKeyboard()
        initialiseAnimation()
        hideListOption()
        addWatchListenerToInputSearchLineText()
        addCallbackToDeleteIconDeleteText()
        addOnClickListenerToLineOnList()

    }


    private fun getAccessToViewFromOtherFragmentOrActivity(){
        if(bottomNavView == null){
            bottomNavView=requireActivity().findViewById(R.id.nav_view)
            findMyLocationOnMapFAB=requireActivity().findViewById(R.id.locationfab)
            showAllOrFavoriteBusOnMapFAB=requireActivity().findViewById(R.id.Map_showAllVehiclesOrFavorite)
            showVehicleStopOrVehiclesFAB=requireActivity().findViewById(R.id.busStopButton)
        }

    }

    private fun hideApplicationNavBar(){
        getAccessToViewFromOtherFragmentOrActivity()
        bottomNavView?.visibility=View.GONE
        findMyLocationOnMapFAB?.visibility=View.GONE
        showAllOrFavoriteBusOnMapFAB?.visibility=View.GONE
        showVehicleStopOrVehiclesFAB?.visibility=View.GONE
    }

    private fun showApplicationNavBar(){
        getAccessToViewFromOtherFragmentOrActivity()
        bottomNavView?.visibility=View.VISIBLE
        findMyLocationOnMapFAB?.visibility=View.VISIBLE
        showAllOrFavoriteBusOnMapFAB?.visibility=View.VISIBLE
        showVehicleStopOrVehiclesFAB?.visibility=View.VISIBLE
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
        if(_binding!=null){
           binding.searchIcon.setImageResource(R.drawable.back_icon_green)
        }

    }

    private fun changeSearchIconToNoActiveSearchIcon(){
        if(_binding!=null){
            binding.searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
        }
    }



    private fun initialiseAnimation(){
        prepareRotateIconSearchAnimation()
        prepareResizeIconSearchAnimation()
    }


    private fun prepareRotateIconSearchAnimation(){
        AnimationSearchView.preparedRotateIconSearchAnimation(binding.searchIcon)

    }


    private fun prepareResizeIconSearchAnimation(){
       AnimationSearchView.prepareResizeIconSearchAnimation(binding.searchIcon)
    }



    private fun runOpenSearchPanelAnimation(){

        val alfaBackgroundWhenShowSearchView=40

        hideApplicationNavBar()
        binding.root.setBackgroundColor(Color.GRAY)
        binding.root.background.alpha=alfaBackgroundWhenShowSearchView

        AnimationSearchView.rotateAnimation.doOnEnd {
            changeSearchIconToActiveSearchIcon()
            val animationSet=AnimatorSet()
            animationSet.playTogether(AnimationSearchView. scaleXAnimationScaleUo,AnimationSearchView. scaleYAnimationScaleUp)
            animationSet.start()

        }

        val animationSet = AnimatorSet()
        animationSet.playTogether(AnimationSearchView .scaleYAnimationScaleDown, AnimationSearchView. scaleXAnimationScaleDown,AnimationSearchView.rotateAnimation)
        animationSet.start()
    }

    private fun actionWhenSearchPanelClickIcon(){
        val searchIcon=binding.searchIcon


        searchIcon.setOnClickListener {

            if(isShowKeyboard){
            hideKeyboard()
            binding.searchEditText.clearFocus()


        }else{
            binding.searchEditText.requestFocus()
            showKeyboard()


        }}
    }


    override fun onStart() {
        super.onStart()
        addListenerToKeyboard()


        adapter.setRefresh {
            adapter.changeDataset(getLineMatchToUserPattern())
            adapter.notifyDataSetChanged()

            refreshAction?.let { it() }

        }

    }

    private var mLastContentHeight = 0
    private val keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
    val keyboardExpectArea=100

        try{

            val currentContentHeight: Int =
                requireActivity().findViewById<View>(Window.ID_ANDROID_CONTENT).height
            if (mLastContentHeight > currentContentHeight + keyboardExpectArea) {
                isShowKeyboard=true
                mLastContentHeight = currentContentHeight
            } else if (currentContentHeight > mLastContentHeight + keyboardExpectArea) {
                mLastContentHeight = currentContentHeight
                isShowKeyboard=false

                val editTxt=binding.searchEditText
                editTxt.clearFocus()

            }

        }catch (_:Exception ){

        }



    }
    private fun addListenerToKeyboard(){
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).viewTreeObserver.
        addOnGlobalLayoutListener(keyboardLayoutListener)

    }



    private fun hideListOption(){
        hideListSearchViewAnimation()
    }

    private fun showListOption(){
        binding.allSearchPane.visibility=View.VISIBLE
        showListSearchViewAnimation()
    }


    private fun runCloseSearchPanelAnimation(){
        showApplicationNavBar()
        hideKeyboard()
        val maxVisible=1

        binding.root.setBackgroundColor(Color.WHITE)
        binding.root.background.alpha=maxVisible
        binding.searchEditText.clearFocus()
        changeSearchIconToActiveSearchIcon()
        val animationSet = AnimatorSet()
        animationSet.playTogether(AnimationSearchView.  scaleYAnimationScaleDown,AnimationSearchView.   scaleXAnimationScaleDown)
        animationSet.start()


    AnimationSearchView.    scaleYAnimationScaleDown.doOnEnd {
            val animationSetEnd=AnimatorSet()
            animationSetEnd.playTogether(AnimationSearchView.  scaleXAnimationScaleUo,AnimationSearchView.  scaleYAnimationScaleUp)
            animationSetEnd.start()
            changeSearchIconToNoActiveSearchIcon()
            AnimationSearchView.rotateAnimationReverse.start()
        }

    }



    private fun showDeleteTextIconWhenIsMinimumOneCharacterOrHide(){
        val deleteIcon=binding.deleteUserWriteTextIcon
        if(binding.searchEditText.text.toString().isNotEmpty()){

            deleteIcon.visibility=View.VISIBLE
        }else{
            deleteIcon.visibility=View.INVISIBLE
        }


    }


    private  fun getMatchNumberLineFromDatabase(numberLinePattern:String):ArrayList<LineData>{
            return Api.getApi().getInfoAboutLinePatternNumber(numberLinePattern.toInt())
    }

    private fun getMatchAnyVehicleStopLineFromDatabase(patternVehicleStop:String):ArrayList<LineData>{
        return Api.getApi().getInfoAboutLinePatternAnyVehicleStop(patternVehicleStop)
    }


    private fun ifWriteNumberLine(patternSerchTextWriteByUser:String):Boolean{
        if(patternSerchTextWriteByUser.toIntOrNull()!=null){
            return true
        }
        return false
    }


    private fun getLineMatchToUserPattern():ArrayList<LineData>{
        val minimumCharToStartFindingLine=2
        val textFromSearchInput=binding.searchEditText.text.toString()
        var matchLines:ArrayList<LineData> =ArrayList()
        if(ifWriteNumberLine(textFromSearchInput)){
            matchLines=getMatchNumberLineFromDatabase(textFromSearchInput)
        }else{

            if(textFromSearchInput.length>minimumCharToStartFindingLine){
                matchLines=getMatchAnyVehicleStopLineFromDatabase(textFromSearchInput)
            }
        }

        matchLines.addAll(Api.getApi().getAllVehicleStopAsLineData(textFromSearchInput))
        return matchLines

    }


    private fun changeDataSetAdapterLineMatchToUserText(){
        adapter.changeDataset(getLineMatchToUserPattern())
        adapter.notifyDataSetChanged()
    }

    private fun addWatchListenerToInputSearchLineText(){

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try{
                    changeDataSetAdapterLineMatchToUserText()
                }
                catch (_:Exception){

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showDeleteTextIconWhenIsMinimumOneCharacterOrHide()
            }
        })
    }


    private fun addCallbackToDeleteIconDeleteText(){
        val deleteIcon=binding.deleteUserWriteTextIcon
        val searchEditText=binding.searchEditText
        val defaultValue=""
        deleteIcon.setOnClickListener {
            searchEditText.setText(defaultValue)
        }
    }




    private fun addAnimIconSearchWhenUserFocusSearchBar(){
        val searchEditText=binding.searchEditText
        searchEditText.setOnFocusChangeListener { _, hasFocus ->


            if (hasFocus) {
                runOpenSearchPanelAnimation()
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

typealias RefreshHandler = () -> Unit