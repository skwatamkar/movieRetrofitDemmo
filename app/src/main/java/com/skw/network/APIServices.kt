package com.skw.network

import com.skw.model.movieModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET(".")
    fun getMovieDetails(@Query("apikey") apikey:String,
                        @Query("t") movieTitle:String,
                        @Query("plot") plot:String)
    : Observable<movieModel>
}