package com.moaazelneshawy.sallacodechallenge.data.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.databinding.BindingAdapter

/**
Created by Moaaz Elneshawy
on 06,August,2021
 **/

/*
shapeType: Int = GradientDrawable.RECTANGLE,
    backgroundColor: Int = Color.DKGRAY,
    cornersRadius: Float = 0f,
    topRightRadius: Float = 0f,
    topLeftRadius: Float = 0f,
    bottomRightRadius: Float = 0f,
    bottomLeftRadius: Float = 0f,
    strokeWidth: Int = 0,
    strokeColor: Int = Color.WHITE,
    paddingLeft: Int = 0,
    paddingTop: Int = 0,
    paddingRight: Int = 0,
    paddingBottom: Int = 0,
 */

@BindingAdapter(
    requireAll = false,
    value = [
        "shapeType",
        "backgroundColor",
        "cornersRadius",
        "topRightRadius",
        "topLeftRadius",
        "bottomRightRadius",
        "bottomLeftRadius",
        "strokeWidth",
        "strokeColor",
        "viewPaddingLeft",
        "viewPaddingTop",
        "viewPaddingRight",
        "viewPaddingBottom",
    ]
)
fun customShapeAdapter(
    view: View,
    shapeType: Int = GradientDrawable.RECTANGLE,
    backgroundColor: Int = Color.DKGRAY,
    cornersRadius: Float = 0f,
    topRightRadius: Float = 0f,
    topLeftRadius: Float = 0f,
    bottomRightRadius: Float = 0f,
    bottomLeftRadius: Float = 0f,
    strokeWidth: Int = 0,
    strokeColor: Int = Color.WHITE,
    paddingLeft: Int = 0,
    paddingTop: Int = 0,
    paddingRight: Int = 0,
    paddingBottom: Int = 0
) {
    val drawable = GradientDrawable()
    drawable.apply {
        shape = shapeType
        setColor(backgroundColor)
        cornerRadius = cornersRadius
        setStroke(strokeWidth, strokeColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }
        setStroke(0, Color.WHITE)

    }
    if ((topLeftRadius > 0f) || (topRightRadius > 0f) || (bottomLeftRadius > 0f) || (bottomRightRadius > 0f)) {
        drawable.cornerRadii =
            floatArrayOf(
                topLeftRadius.dpToPx,
                topLeftRadius.dpToPx,
                topRightRadius.dpToPx,
                topRightRadius.dpToPx,
                bottomLeftRadius.dpToPx,
                bottomLeftRadius.dpToPx,
                bottomRightRadius.dpToPx,
                bottomRightRadius.dpToPx,
            )
    }
    view.background = drawable
}

