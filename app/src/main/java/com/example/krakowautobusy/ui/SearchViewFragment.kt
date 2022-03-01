package com.example.krakowautobusy.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchViewBinding.inflate(inflater, container, false)




        val root: View = binding.root







        return root
    }


    fun allowAccessKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAnimIconSearchWhenUserFocusSearchBar()
        actionWhenSearchPanelClickIcon()
        allowAccessKeyboard()
        initialiseAnimation()






    }




    private fun getAccessToNavView(){
        if(bottomNavView == null){
            bottomNavView=requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        }

    }

    private fun hideApplicationNavBar(){
        getAccessToNavView()
        bottomNavView?.visibility=View.GONE
    }

    private fun showApplicationNavBar(){
        getAccessToNavView()
        bottomNavView?.visibility=View.VISIBLE

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
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }





    fun changeSearchIconToActiveSearchIcon(){
        var searchIcon=binding.searchIcon
        searchIcon.setImageResource(R.drawable.back_icon_green)
    }

    fun changeSearchIconToNoActiveSearchIcon(){
        var searchIcon=binding.searchIcon
        searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
    }



    fun initialiseAnimation(){
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
        val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 0f, 1.0f)
        val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 0f, 1.0f)
        scaleX.duration=ANIM_DURATION_MS
        scaleY.duration=ANIM_DURATION_MS

        scaleXAnimationScaleUo=scaleX
        scaleYAnimationScaleUp=scaleY


        val scaleYDown = ObjectAnimator.ofFloat(searchIcon, "scaleY", 1f, 0f)
        val scaleXDown = ObjectAnimator.ofFloat(searchIcon, "scaleX", 1f, 0f)
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
            var animationSet=AnimatorSet()
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
            changeSearchIconToActiveSearchIcon()

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
        requireActivity()?.findViewById<BottomNavigationView>(R.id.nav_view).viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener);

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

            var animationSet=AnimatorSet()
            animationSet.playTogether(scaleXAnimationScaleUo,scaleYAnimationScaleUp)
            animationSet.start()
            changeSearchIconToNoActiveSearchIcon()
            rotateAnimationReverse.start()
        }

    }


    fun addAnimIconSearchWhenUserFocusSearchBar(){

        var searchEditText=binding.searchEditText


        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                runOpenSearchPanelAnimation()

            } else {

                runCloseSearchPanelAnimation()

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