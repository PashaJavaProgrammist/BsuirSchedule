package com.haretskiy.pavel.bsuirschedule

import android.os.Bundle
import android.widget.Toast
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import okhttp3.ResponseBody
import javax.inject.Inject

class GroupsActivity : BaseActivity() {

    override fun getResLayout() = R.layout.activity_groups

    @Inject
    lateinit var restApi: RestApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        restApi.allGroupsList.enqueue(object : BaseCallBack<List<Group>> {
            override fun onSuccess(response: List<Group>?) {
                Toast.makeText(this@GroupsActivity, "onSuccess", Toast.LENGTH_SHORT).show()
            }

            override fun onError(code: Int?, errorBody: ResponseBody?) {
                Toast.makeText(this@GroupsActivity, "onError", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(this@GroupsActivity, "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
