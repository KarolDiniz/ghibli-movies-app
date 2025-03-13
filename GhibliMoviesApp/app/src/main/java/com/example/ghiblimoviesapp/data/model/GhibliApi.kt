package com.example.ghiblimoviesapp.data.api

import com.example.ghiblimoviesapp.data.model.GhibliMovie
import retrofit2.http.GET
import retrofit2.http.Path

interface GhibliApi {
    @GET("films")
    suspend fun getAllMovies(): List<GhibliMovie>

    @GET("films/{id}")
    suspend fun getMovie(@Path("id") id: String): GhibliMovie
}