package com.moaazelneshawy.sallacodechallenge.core

import android.app.Application
import android.content.Context
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/

@HiltAndroidApp
class BaseApplication : Application()