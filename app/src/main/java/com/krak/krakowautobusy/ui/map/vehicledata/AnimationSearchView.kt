package com.krak.krakowautobusy.ui.map.vehicledata

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class AnimationSearchView {
    companion object {

        private const val animDurationMs=800L
        fun  showListSearchViewAnimation(view: View) {
                view.pivotX = 0f
                view.pivotY = 0f

                view.animate()
                    .scaleY(1f)
                    .setInterpolator(AccelerateDecelerateInterpolator()).duration = animDurationMs


        }



       fun hideListSearchViewAnimation(view: View){

            view.pivotX = 0f
            view.pivotY = 0f

            view.animate()
                .scaleY(0f).withEndAction {
                    view.visibility = View.GONE
                }
                .setInterpolator(AccelerateDecelerateInterpolator()).duration = animDurationMs

        }

         lateinit var rotateAnimation:ObjectAnimator
         lateinit var rotateAnimationReverse:ObjectAnimator


         private const val rotateMinusAnim=-180f
         private const val rotateBaseAnim=0f
         private const val rotatePlusAnim=180f

         fun preparedRotateIconSearchAnimation(searchIcon:View){

            val animatorRotateIcon = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, rotateMinusAnim, rotateBaseAnim)
            animatorRotateIcon.duration = animDurationMs
            rotateAnimation=animatorRotateIcon

            val animatorRotateIconReverse = ObjectAnimator.ofFloat(searchIcon, View.ROTATION, rotatePlusAnim, rotateBaseAnim)
            animatorRotateIconReverse.duration = animDurationMs
            rotateAnimationReverse=animatorRotateIconReverse


        }



         lateinit var scaleXAnimationScaleUo:ObjectAnimator
         lateinit var scaleYAnimationScaleUp:ObjectAnimator

         lateinit var scaleXAnimationScaleDown:ObjectAnimator
         lateinit var scaleYAnimationScaleDown:ObjectAnimator
         private const val animScaleZero=0f
         private const val animScaleUp=1.5f

         fun prepareResizeIconSearchAnimation(searchIcon: View){

            val scaleY = ObjectAnimator.ofFloat(searchIcon, "scaleY", animScaleZero, animScaleUp)
            val scaleX = ObjectAnimator.ofFloat(searchIcon, "scaleX", animScaleZero, animScaleUp)
            scaleX.duration=animDurationMs
            scaleY.duration=animDurationMs

            scaleXAnimationScaleUo=scaleX
            scaleYAnimationScaleUp=scaleY


            val scaleYDown = ObjectAnimator.ofFloat(searchIcon, "scaleY", animScaleUp, animScaleZero)
            val scaleXDown = ObjectAnimator.ofFloat(searchIcon, "scaleX", animScaleUp, animScaleZero)
            scaleXDown.duration=animDurationMs
            scaleYDown.duration=animDurationMs
            scaleXAnimationScaleDown=scaleXDown
            scaleYAnimationScaleDown=scaleYDown


        }




    }
}