package com.vinicius.marvelheroes.utils.extensions

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.palette.graphics.Palette

fun Drawable.paint(bitmap: Bitmap) {
    Palette.from(bitmap).generate {
        val defaultColor = 0x000000
        val color = it?.getVibrantColor(defaultColor)
        color?.let { it1 ->
            if (this is ShapeDrawable) {
                this.paint.color = it1
            } else if (this is GradientDrawable) {
                this.setColor(it1)
            } else if (this is ColorDrawable) {
                this.color = it1
            }
        }
    }
}