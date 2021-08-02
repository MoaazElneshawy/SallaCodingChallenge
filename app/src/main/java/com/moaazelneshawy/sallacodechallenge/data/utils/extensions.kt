package com.moaazelneshawy.sallacodechallenge.data.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.View

/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/


fun View.applyCustomShape(
    shapeType: Int = GradientDrawable.RECTANGLE,
    backgroundColor: Int = Color.DKGRAY,
    cornersRadius: Float = 0f,
    strokeWidth: Int = 0,
    strokeColor: Int = Color.WHITE,
    paddingLeft: Int = 0,
    paddingTop: Int = 0,
    paddingRight: Int = 0,
    paddingBottom: Int = 0,
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
    this.background = drawable
}

fun Context.log(message: String) {
    Log.e(this.javaClass.simpleName, message)
}