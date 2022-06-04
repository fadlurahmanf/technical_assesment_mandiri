package com.technical_assessment.starterappmvvm.data.response.movie

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    var genres:ArrayList<GenresResponse.Genre> ?= null
){
    data class Genre(
        @SerializedName("id")
        var id:Int ?= null,
        @SerializedName("name")
        var name:String ?= null
    )
}
