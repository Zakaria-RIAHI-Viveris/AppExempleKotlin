package com.viveris.android.appexemplekotlin.ui.view

import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout

/**
 * This class manage the display of a loading overlay
 */
class ProgressBarManager {

    /**
     * display/hide a loading overlay
     * @param isLoading to know if we display or hide the frame layout
     * @param progressBarHolder the pregress bar id
     */
    fun onLoaderStateChange(isLoading: Boolean, progressBarHolder: FrameLayout?) {
        progressBarHolder?.apply {
            val animation: AlphaAnimation = when (isLoading) {
                true -> AlphaAnimation(0f, 1f)
                else -> AlphaAnimation(1f, 0f)
            }
            animation.duration = 200
            this.animation = animation
            visibility = when (isLoading) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }
}