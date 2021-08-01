package com.moaazelneshawy.sallacodechallenge.di

import android.content.Context
import com.moaazelneshawy.sallacodechallenge.core.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/


@Module
@InstallIn(value = [SingletonComponent::class])
class AppModule {

    @Singleton
    @Provides
    fun baseApplication(@ApplicationContext app:Context):BaseApplication{
        return  app as BaseApplication
    }

}