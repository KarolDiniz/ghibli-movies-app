package com.example.ghiblimoviesapp.data.repository

import com.example.ghiblimoviesapp.data.api.RetrofitClient
import com.example.ghiblimoviesapp.data.model.GhibliMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GhibliRepository {
    private val api = RetrofitClient.api

    suspend fun getAllMovies(): List<GhibliMovie> = withContext(Dispatchers.IO) {
        api.getAllMovies()
    }

    suspend fun getMovie(id: String): GhibliMovie = withContext(Dispatchers.IO) {
        api.getMovie(id)
    }
}