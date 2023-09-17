package com.example.caesarappgithub.Itemgithub.Api
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchData(
        @Query("q") name: String,
        @Query("per_page") limit : Int = 18
    ) : Call<SearchResponse>


    @GET("users/{name}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("name") name : String,
    ) : Call<DetailUserResponse>

    @Headers("Authorization: token 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 ")
    @GET("users/{name}/followers")
    fun getFollowers(
        @Path("name") name : String
    ) : Call<List<FollowResponseItem>>


    @GET("users/{name}/following")
    @Headers("Authorization: token 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 ")
    fun getFollowing(
        @Path("name") name : String
    ) : Call<List<FollowResponseItem>>

}