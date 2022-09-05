package com.example.krakowautobusy.ui.map.vehicledata

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
import com.example.krakowautobusy.BundleChoiceVehicle
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.LineData
import com.example.krakowautobusy.database.VehicleStopData
import com.example.krakowautobusy.databinding.FragmentSearchViewBinding
import com.example.krakowautobusy.databinding.FragmentSearchViewVehicleStopBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchViewVehicleStop.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchViewVehicleStop : Fragment() {
    private var _binding: FragmentSearchViewVehicleStopBinding? = null
    private var isShowKeyboard=false
    private  var bottomNavView: BottomNavigationView?=null

    private var showAllOrFavoriteBusOnMap_FAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    private var findMyLocationOnMap_FAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    val ANIM_DURATION_MS=800L

    private   var refreshH: RefreshHandler? = null;


    private lateinit var   adapter :AdapterListSearchVehicleStop;

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


    public fun setRefreshFun(zm:RefreshHandler ){
        refreshH=zm
        //  adapter.setRefresh(zm)
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
    }

    private fun hideListSearchViewAnimation(){
        AnimationSearchView.hideListSearchViewAnimation(binding.allSearchPane)
    }



    fun addOnClickListenerToLineOnList(){
        binding.searchList.setOnItemClickListener { _, view, _, _ ->
            val bundle = bundleOf(
                "nameVehicleStop" to
                    view.findViewById<TextView>(R.id.nameVehicleStop).text.toString().trim()



            )

            Navigation.findNavController(view).navigate(R.id.action_navigate_to_details_vehiclestop,bundle);
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
            bottomNavView=requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            findMyLocationOnMap_FAB=requireActivity().findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.locationfab)
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

        //  if(refreshH!=null){
        //      refreshH();
        //  }

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
        //  val searchIcon=binding.searchIcon
        if(_binding!=null){
            binding.searchIcon.setImageResource(R.drawable.back_icon_green)
        }
        //
    }

    private fun changeSearchIconToNoActiveSearchIcon(){
//        val searchIcon=binding.searchIcon
        //      searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
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

        val ALFA_BACGROUND_WHEN_SHOW_SEARCH_VIEW_PERCENTAGE=40
        hideApplicationNavBar()
        binding.root.setBackgroundColor(Color.GRAY)
        binding.root.background.alpha=ALFA_BACGROUND_WHEN_SHOW_SEARCH_VIEW_PERCENTAGE
        //  binding.allSearchPane.alpha=0.9f



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

                //  changeSearchIconToActiveSearchIcon()

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


            refreshH?.let { it() }



        }

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
                editTxt.clearFocus()

            }

        }catch (_:Exception ){

        }



    }
    private fun addListenerToKeyboard(){
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).viewTreeObserver.
        addOnGlobalLayoutListener(keyboardLayoutListener);

    }



    private fun hideListOption(){
        hideListSearchViewAnimation()
        //  binding.allSearchPane.visibility=View.GONE
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


        val animationSet = AnimatorSet()
        animationSet.playTogether(AnimationSearchView.  scaleYAnimationScaleDown,AnimationSearchView.   scaleXAnimationScaleDown)
        animationSet.start()


        AnimationSearchView.    scaleYAnimationScaleDown.doOnEnd {
            //  binding.allSearchPane.visibility=View.GONE
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


    private  fun getMatchNumberLineFromDatabase(numberLinePattern:String):ArrayList<LineData>{
        //return Api.getApi().getVe
        return Api.getApi().getInfoAboutLinePatternNumber(numberLinePattern.toInt());

    }

    private fun getMatchAnyVehicleStopLineFromDatabase(patternVehicleStop:String):ArrayList<VehicleStopData>{
        return Api.getApi().getAllVehiclesStop()
    }


    private fun ifWriteNumberLine(patternSerchTextWriteByUser:String):Boolean{
        if(patternSerchTextWriteByUser.toIntOrNull()!=null){
            return true
        }
        return false
    }


    private fun getLineMatchToUserPattern():ArrayList<VehicleStopData>{
        val MINIMUM_CHAR_TO_START_SEARCHING_MATCH_LINE=1
        val textFromSearchInput=binding.searchEditText.text.toString()
        var matchLines:ArrayList<VehicleStopData> =ArrayList<VehicleStopData>()
       // if(ifWriteNumberLine(textFromSearchInput)){
          //  matchLines=getMatchNumberLineFromDatabase(textFromSearchInput)
      //  }else{

            if(textFromSearchInput.length>MINIMUM_CHAR_TO_START_SEARCHING_MATCH_LINE){
                matchLines=getMatchAnyVehicleStopLineFromDatabase(textFromSearchInput)
                matchLines= matchLines.filter { it.name.contains(textFromSearchInput) } as ArrayList<VehicleStopData>
            }
       // }
        return matchLines

    }

    public fun getAdapter():AdapterListSearchVehicleStop{
        return adapter;

    }

    private fun changeDataSetAdapterLineMatchToUserText(){
        // if(refreshH!=null){
//
        //     refreshH()
        //  }

        adapter.changeDataset(getLineMatchToUserPattern())
        adapter.notifyDataSetChanged()
    }

    private fun addWatchListenerToInputSearchLineText(){

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try{//Sprawdź czy to jest konieczne aby dawać try
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
        deleteIcon.setOnClickListener {
            searchEditText.setText(  "")
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