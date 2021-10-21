package com.example.week5_sec4_jsonpractice

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/contacts/")
    fun getNameJ(): Call<ArrayList<NameJ?>?>
}