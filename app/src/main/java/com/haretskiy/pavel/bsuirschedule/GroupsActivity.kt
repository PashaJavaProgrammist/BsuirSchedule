package com.haretskiy.pavel.bsuirschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import okhttp3.ResponseBody
import javax.inject.Inject

class GroupsActivity : AppCompatActivity() {

    @Inject
    lateinit var restApi: RestApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        daggerComponent.inject(this)

        restApi.allGroupsList.enqueue(object : BaseCallBack<List<Group>> {
            override fun onSuccess(response: List<Group>?) {

            }

            override fun onError(code: Int?, errorBody: ResponseBody?) {
            }

            override fun onFailure(t: Throwable) {
            }
        })
    }
}
