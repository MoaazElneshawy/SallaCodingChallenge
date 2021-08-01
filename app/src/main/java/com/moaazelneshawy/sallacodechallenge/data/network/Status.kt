package com.moaazelneshawy.sallacodechallenge.data.network

import androidx.annotation.IntDef

/**
 * ِAmer Elsayed
 * dev.amer.elsayed@gmail.com
 * 01/01/2020
 */

@IntDef(LOADING, SUCCESS, ERROR)
@Retention(AnnotationRetention.SOURCE)
annotation class Status

const val LOADING = 0
const val SUCCESS = 1
const val ERROR = 2