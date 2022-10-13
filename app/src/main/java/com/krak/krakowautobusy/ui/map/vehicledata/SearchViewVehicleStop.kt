package com.krak.krakowautobusy.ui.map.vehicledata

import android.animation.AnimatorSet
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.VehicleStopData
import com.krak.krakowautobusy.databinding.FragmentSearchViewVehicleStopBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [SearchViewVehicleStop.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchViewVehicleStop : Fragment() {
    private var _binding: FragmentSearchViewVehicleStopBinding? = null
    private var isShowKeyboard=false
    private  var bottomNavView: BottomNavigationView?=null

    private var showAllOrFavoriteBusOnMapFAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    private var findMyLocationOnMapFAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null


    private   var refreshAction: RefreshHandler? = null
    private lateinit var   adapter :AdapterListSearchVehicleStop

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchViewVehicleStopBinding.inflate(inflater, container, false)
        val root: View = binding.root
        addAdapterToSearchLineListView()
        return root
    }

    private   var actionShowKeyboard: RefreshHandler? = null
    private   var actionHidekeyboard: RefreshHandler? = null



     fun setActionWhenShowKeyboard(action:RefreshHandler){
        actionShowKeyboard=action
    }


     fun setActionWhenHideKeyboard(action:RefreshHandler){
        actionHidekeyboard=action
    }


     fun setRefreshFun(zm:RefreshHandler ){
        refreshAction=zm

    }

    private fun addAdapterToSearchLineListView(){
        adapter= AdapterListSearchVehicleStop(arrayListOf<VehicleStopData>(),requireContext())
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



    private fun addOnClickListenerToLineOnList(){
        val nameVehicleBundleKeyName="nameVehicleStop"
        binding.searchList.setOnItemClickListener { _, view, _, _ ->
            val bundle = bundleOf(
                nameVehicleBundleKeyName to
                    view.findViewById<TextView>(R.id.nameVehicleStop).text.toString().trim()
            )

            Navigation.findNavController(view).navigate(R.id.action_navigate_to_details_vehiclestop,bundle)
        }
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
        }

    }

    private fun hideApplicationNavBar(){
        getAccessToViewFromOtherFragmentOrActivity()
        bottomNavView?.visibility=View.GONE
        findMyLocationOnMapFAB?.visibility=View.GONE
        showAllOrFavoriteBusOnMapFAB?.visibility=View.GONE
    }

    private fun showApplicationNavBar(){
        getAccessToViewFromOtherFragmentOrActivity()
        bottomNavView?.visibility=View.VISIBLE
        findMyLocationOnMapFAB?.visibility=View.VISIBLE
        showAllOrFavoriteBusOnMapFAB?.visibility=View.VISIBLE
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

        val alfaShowPercentageWhenSearchPanelShow=40
        hideApplicationNavBar()
        binding.root.setBackgroundColor(Color.GRAY)
        binding.root.background.alpha=alfaShowPercentageWhenSearchPanelShow

        AnimationSearchView.rotateAnimation.doOnEnd {

            changeSearchIconToActiveSearchIcon()
            val animationSet= AnimatorSet()
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
        val KEYBOARD_EXPECT_AREA=100

        try{

            val currentContentHeight: Int =
                requireActivity().findViewById<View>(Window.ID_ANDROID_CONTENT).height
            if (mLastContentHeight > currentContentHeight + KEYBOARD_EXPECT_AREA) {
                isShowKeyboard=true
                mLastContentHeight = currentContentHeight
            } else if (currentContentHeight > mLastContentHeight + KEYBOARD_EXPECT_AREA) {
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

        binding.searchEditText.clearFocus()
        changeSearchIconToActiveSearchIcon()

        binding.root.setBackgroundColor(Color.WHITE)
        binding.root.background.alpha=1


        val animationSet = AnimatorSet()
        animationSet.playTogether(AnimationSearchView.  scaleYAnimationScaleDown,AnimationSearchView.   scaleXAnimationScaleDown)
        animationSet.start()


        AnimationSearchView.    scaleYAnimationScaleDown.doOnEnd {
            val animationSetEnd= AnimatorSet()
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



    private fun getMatchAnyVehicleStopLineFromDatabase(patternVehicleStop:String):ArrayList<VehicleStopData>{
        return Api.getApi().getAllVehiclesStop()
    }



    private fun getLineMatchToUserPattern():ArrayList<VehicleStopData>{
        val minimumCHarToStartMatchLine=1
        val textFromSearchInput=binding.searchEditText.text.toString()
        var matchLines:ArrayList<VehicleStopData> =ArrayList()

       if(textFromSearchInput.length>minimumCHarToStartMatchLine){
            matchLines=getMatchAnyVehicleStopLineFromDatabase(textFromSearchInput.lowercase())
            matchLines= matchLines.filter { it.name.lowercase().contains(textFromSearchInput.lowercase()) } as ArrayList<VehicleStopData>
       }

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
            searchEditText.setText( defaultValue)
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