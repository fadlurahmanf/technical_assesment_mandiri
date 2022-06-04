package com.technical_assessment.starterappmvvm.data.entity.example

import android.content.Context
import com.technical_assessment.starterappmvvm.base.network.AbstractNetwork
import com.technical_assessment.starterappmvvm.data.api.example.ExampleApi
import javax.inject.Inject

class ExampleEntity @Inject constructor(
    var context: Context
): AbstractNetwork<ExampleApi>() {
    override fun getApi(): Class<ExampleApi> {
        return ExampleApi::class.java
    }

    fun getTestimonial() = networkService(30).getTestimonial()

}