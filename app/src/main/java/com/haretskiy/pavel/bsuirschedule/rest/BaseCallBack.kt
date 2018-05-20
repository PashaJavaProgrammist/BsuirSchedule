package com.haretskiy.pavel.bsuirschedule.rest

import com.haretskiy.pavel.bsuirschedule.SUCCESS_CODE
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface BaseCallBack<T> : Callback<T> {

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        if (t != null) onFailure(t)
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response?.code() == SUCCESS_CODE) {
            onSuccess(response.body())
        } else {
            onError(response?.code(), response?.errorBody())
        }
    }

    fun onError(code: Int?, errorBody: ResponseBody?)

    fun onSuccess(response: T?)

    fun onFailure(t: Throwable)

}