package com.moaazelneshawy.sallacodechallenge.data.network

import android.util.Log
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moaazelneshawy.sallacodechallenge.core.BaseRsm
import com.moaazelneshawy.sallacodechallenge.data.network.Resource.Companion.failure
import com.moaazelneshawy.sallacodechallenge.data.network.Resource.Companion.success
import kotlinx.coroutines.Dispatchers
import retrofit2.Response


/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/

fun <T> loadData(request: suspend () -> Response<BaseRsm<T>>) = liveData(Dispatchers.IO) {
    emit(Resource.loading())
    try {
        val response = request.invoke()
        val rsm = if (response.isSuccessful) response.body() else {
            val type = object : TypeToken<BaseRsm<T>>() {}.type
            Gson().fromJson(response.errorBody()!!.charStream(), type) as BaseRsm<T>
        }

        if (response.isSuccessful && rsm != null) {
            if (response.code() == 200) {
                Log.i("responseStatus", "${rsm.status}")
                if (rsm.status == 200)
                    emit(success(rsm))
                else emit(failure("rsm.status"))
            }
        }

    } catch (e: Exception) {
        Log.e("okhttp", "$e")
        emit(failure(e.localizedMessage))
    }
}
