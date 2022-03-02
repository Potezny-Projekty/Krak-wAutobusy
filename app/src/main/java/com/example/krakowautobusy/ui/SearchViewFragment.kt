package com.example.krakowautobusy.ui


import LineBusData
import Vehicle
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.AdapterListSearchPanel

import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.FragmentSearchViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SearchViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentSearchViewBinding? = null
    private var isShowKeyboard=false
    private  var bottomNavView:BottomNavigationView?=null

    private var showAllOrFavoriteBusOnMap_FAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    private var findMyLocationOnMap_FAB:com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton?=null
    val ANIM_DURATION_MS=800L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchViewBinding.inflate(inflater, container, false)




        val root: View = binding.root







        return root
    }


    private fun allowAccessKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
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



    }




    private fun addDataToSearchView(){
      val dataModels = ArrayList<LineBusData>()
      dataModels.add(LineBusData(1,Vehicle.BUS,537,"Witkowice","Dworzec Główny Wschód",true))
      dataModels.add(LineBusData(2,Vehicle.BUS,112,"Os,Podwawelskie","Tyniec Kamieniołom",false))
      dataModels.add(LineBusData(3,Vehicle.TRAM,5,"Wzgórze Krzesłowickie","Krowodrza Górka",true))
      dataModels.add(LineBusData(4,Vehicle.TRAM,17,"Czerwone Maki P+R","Dworzec Towarowy",false))

      val  adapter = AdapterListSearchPanel(dataModels,requireContext())

        binding.searchList.adapter = adapter
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
        val searchIcon=binding.searchIcon
        searchIcon.setImageResource(R.drawable.back_icon_green)
    }

    private fun changeSearchIconToNoActiveSearchIcon(){
        val searchIcon=binding.searchIcon
        searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
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
        binding.allSearchPane.visibility=View.GONE
    }

    private fun showListOption(){
        binding.allSearchPane.visibility=View.VISIBLE
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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchView.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}