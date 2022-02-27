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
import android.widget.EditText
import android.widget.RelativeLayout
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


        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)




        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAnimIconSearchWhenUserFocusSearchBar()
        closeSearchAfterClickIcon()






    }


    private fun hideBottomNavigationView(view: BottomNavigationView) {
    //    view.animate().translationY(view.height.toFloat())
    }

    private fun showBottomNavigationView(view: BottomNavigationView) {
      //  view.animate().translationY(0f)
    }


    fun hideApplicationBar(){
        val navBar=requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
hideBottomNavigationView(navBar)


        val container=requireActivity().findViewById<View>(R.id.main_fragment)
        val lp = RelativeLayout.LayoutParams(container.getLayoutParams())
     //   lp.setMargins(0,0, 0, 50)
       container.setLayoutParams(lp)
      //  val navBar=requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        navBar?.visibility=View.GONE
    }

    fun showApplicationBar(){
        val navBar=requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        showBottomNavigationView(navBar)
        navBar?.visibility=View.VISIBLE
        val container=requireActivity().findViewById<View>(R.id.main_fragment)
        val lp = RelativeLayout.LayoutParams(container.getLayoutParams())
      //  lp.setMargins(0,0, 0, 0)
       // container.setLayoutParams(lp)


    }





    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }



    /*odkomentuj
    private var mLastContentHeight = 0

    fun xD(){
        val keyboardLayoutListener = OnGlobalLayoutListener {
            val currentContentHeight: Int = requireActivity().findViewById<View>(Window.ID_ANDROID_CONTENT).getHeight()
            if (mLastContentHeight > currentContentHeight + 100) {
                Log.e("animacje","onGlobalLayout: Keyboard is open")
                mLastContentHeight = currentContentHeight
            } else if (currentContentHeight > mLastContentHeight + 100) {
                Log.e("animacje","close")
                mLastContentHeight = currentContentHeight


                binding.searchEditText.clearFocus()
            }
        }

        binding.root.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

    }*/
    fun closeSearchAfterClickIcon(){
        val searchIcon=binding.searchIcon

        val ANIM_DURATION_MS=800L
        searchIcon.setOnClickListener {
            hideKeyboard()
            binding.searchEditText.clearFocus()
            searchIcon.setImageResource(R.drawable.back_icon_green)
            val animationSet2 = AnimatorSet()
            val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 1.5f, 0f)
            val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 1.5f, 0f)



            scaleX.duration=ANIM_DURATION_MS
            scaleY.duration=ANIM_DURATION_MS


            animationSet2.playTogether(scaleX, scaleY)
            animationSet2.start()





            scaleX.doOnEnd {

                val animationSet2 = AnimatorSet()
                val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 0f, 1f)
                val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 0f, 1f)



                scaleX.duration=ANIM_DURATION_MS
                scaleY.duration=ANIM_DURATION_MS


                animationSet2.playTogether(scaleX, scaleY)
                animationSet2.start()


                searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
                val animatorRotateIcon = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, 180f, 0f)
                animatorRotateIcon.duration = ANIM_DURATION_MS
                animatorRotateIcon.start()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        xD()
    }

    private var mLastContentHeight = 0
    val keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val currentContentHeight: Int =
            requireActivity().findViewById<View>(Window.ID_ANDROID_CONTENT).getHeight()
        if (mLastContentHeight > currentContentHeight + 100) {
            Log.e("animacje", "onGlobalLayout: Keyboard is open")
            mLastContentHeight = currentContentHeight
        } else if (currentContentHeight > mLastContentHeight + 100) {
            Log.e("animacje", "close")
            mLastContentHeight = currentContentHeight


            val editTxt=requireActivity().findViewById<EditText>(R.id.search_edit_text)
            if(editTxt !=null){
                editTxt.clearFocus()
            }else{
                Log.e("czy","null")
            }

        }
    }
    fun xD(){


        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

    }



    fun addAnimIconSearchWhenUserFocusSearchBar(){
        var searchIcon=binding.searchIcon
        var searchEditText=binding.searchEditText

        val ANIM_DURATION_MS=800L
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                hideApplicationBar()

                binding.root.setBackgroundColor(Color.GRAY)
                binding.root.background.alpha=40

                val animatorRotateIcon = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, -180f, 0f)
                animatorRotateIcon.duration = ANIM_DURATION_MS


                animatorRotateIcon.doOnEnd {
                    searchIcon.scaleX=1.0f
                    searchIcon.scaleY=1f

                    searchIcon.setImageResource(R.drawable.back_icon_green)
                    val animationSet = AnimatorSet()
                    val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 0f, 1.5f)
                    val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 0f, 1.5f)



                    scaleX.duration=ANIM_DURATION_MS
                    scaleY.duration=ANIM_DURATION_MS





                    animationSet.playTogether(scaleX, scaleY)
                    animationSet.start()


                }



                val animationSet = AnimatorSet()

                val animScaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 1f, 0f)
                val animScaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 1f, 0f)



                animScaleX.duration=ANIM_DURATION_MS
                animScaleY.duration=ANIM_DURATION_MS
                animationSet.playTogether(animScaleX, animScaleY,animatorRotateIcon)
                animationSet.start()



            } else {


showApplicationBar()
                val searchIcon=binding.searchIcon

                val ANIM_DURATION_MS=800L

                    hideKeyboard()
                    binding.searchEditText.clearFocus()
                    searchIcon.setImageResource(R.drawable.back_icon_green)
                    val animationSet2 = AnimatorSet()
                    val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 1.5f, 0f)
                    val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 1.5f, 0f)



                    scaleX.duration=ANIM_DURATION_MS
                    scaleY.duration=ANIM_DURATION_MS


                    animationSet2.playTogether(scaleX, scaleY)
                    animationSet2.start()





                    scaleX.doOnEnd {

                        val animationSet2 = AnimatorSet()
                        val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", 0f, 1f)
                        val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", 0f, 1f)



                        scaleX.duration=ANIM_DURATION_MS
                        scaleY.duration=ANIM_DURATION_MS


                        animationSet2.playTogether(scaleX, scaleY)
                        animationSet2.start()


                        searchIcon.setImageResource(R.drawable.ic_baseline_menu_24)
                        val animatorRotateIcon = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, 180f, 0f)
                        animatorRotateIcon.duration = ANIM_DURATION_MS
                        animatorRotateIcon.start()
                    }







            }
        }


        searchIcon.setOnClickListener {


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