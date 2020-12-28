package com.example.android.retrofitpractice.api

import com.example.android.retrofitpractice.model.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("recipes/{id}/summary")
    fun getRecipe (@Path("id") recipeId:Int,
                   @Query("apiKey") apiKey:String="3a88a09ef93342298911c52465535ae6")
            : Call <Recipe>
}