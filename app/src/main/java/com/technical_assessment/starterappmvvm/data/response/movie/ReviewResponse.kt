package com.technical_assessment.starterappmvvm.data.response.movie

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("id"            ) var id           : Int?               = null,
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Results>? = null,
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
){
    data class Results(
        @SerializedName("author"         ) var author        : String?        = null,
        @SerializedName("author_details" ) var authorDetails : AuthorDetails? = null,
        @SerializedName("content"        ) var content       : String?        = null,
        @SerializedName("created_at"     ) var createdAt     : String?        = null,
        @SerializedName("id"             ) var id            : String?        = null,
        @SerializedName("updated_at"     ) var updatedAt     : String?        = null,
        @SerializedName("url"            ) var url           : String?        = null
    ){
        data class AuthorDetails(
            @SerializedName("name"        ) var name       : String? = null,
            @SerializedName("username"    ) var username   : String? = null,
            @SerializedName("avatar_path" ) var avatarPath : String? = null,
            @SerializedName("rating"      ) var rating     : Int?    = null
        )
    }
}
