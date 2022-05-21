package com.example.krakowautobusy.ui.map.vehicledata

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd

class AnimationSearchView {
    companion object {

        val ANIM_DURATION_MS=800L
        public fun  showListSearchViewAnimation(view: View) {
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



        public fun hideListSearchViewAnimation(view: View){

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

        public lateinit var rotateAnimation:ObjectAnimator
        public lateinit var rotateAnimationReverse:ObjectAnimator

        public fun preparedRotateIconSearchAnimation(searchIcon:View){
           // val searchIcon=binding.searchIcon
            val animatorRotateIcon = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, -180f, 0f)
            animatorRotateIcon.duration = ANIM_DURATION_MS
            rotateAnimation=animatorRotateIcon

           // return animatorRotateIcon
            val animatorRotateIconReverse = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, 180f, 0f)
            animatorRotateIconReverse.duration = ANIM_DURATION_MS
            rotateAnimationReverse=animatorRotateIconReverse


        }




        public lateinit var scaleXAnimationScaleUo:ObjectAnimator
        public lateinit var scaleYAnimationScaleUp:ObjectAnimator

        public lateinit var scaleXAnimationScaleDown:ObjectAnimator
        public lateinit var scaleYAnimationScaleDown:ObjectAnimator
        public fun prepareResizeIconSearchAnimation(searchIcon: View){
           // val searchIcon=binding.searchIcon
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












    }
}