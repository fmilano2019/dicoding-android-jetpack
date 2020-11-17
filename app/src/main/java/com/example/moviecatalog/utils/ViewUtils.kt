package com.example.moviecatalog.utils

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.moviecatalog.R
import com.google.android.material.snackbar.Snackbar

object ViewUtils {

    fun errorSnackbar(
        view: CoordinatorLayout,
        text: String
    ) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            setBackgroundTint(ContextCompat.getColor(context, R.color.design_default_color_error))
            setTextColor(ContextCompat.getColor(context, R.color.colorAccentTransparent))
            show()
        }
    }
}