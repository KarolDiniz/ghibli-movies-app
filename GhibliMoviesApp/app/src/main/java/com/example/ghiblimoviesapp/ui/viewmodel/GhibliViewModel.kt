package com.example.ghiblimoviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghiblimoviesapp.data.model.GhibliMovie
import com.example.ghiblimoviesapp.data.repository.GhibliRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GhibliViewModel : ViewModel() {
    private val repository = GhibliRepository()

    private val _uiState = MutableStateFlow<GhibliUiState>(GhibliUiState.Loading)
    val uiState: StateFlow<GhibliUiState> = _uiState.asStateFlow()

    private val _selectedMovieState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val selectedMovieState: StateFlow<MovieDetailUiState> = _selectedMovieState.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        _uiState.value = GhibliUiState.Loading
        viewModelScope.launch {
            try {
                val movies = repository.getAllMovies()
                _uiState.value = GhibliUiState.Success(movies)
            } catch (e: Exception) {
                _uiState.value = GhibliUiState.Error("Erro ao carregar filmes: ${e.message}")
            }
        }
    }

    fun loadMovie(id: String) {
        _selectedMovieState.value = MovieDetailUiState.Loading
        viewModelScope.launch {
            try {
                val movie = repository.getMovie(id)
                _selectedMovieState.value = MovieDetailUiState.Success(movie)
            } catch (e: Exception) {
                _selectedMovieState.value = MovieDetailUiState.Error("Erro ao carregar detalhes do filme: ${e.message}")
            }
        }
    }
}

sealed class GhibliUiState {
    data object Loading : GhibliUiState()
    data class Success(val movies: List<GhibliMovie>) : GhibliUiState()
    data class Error(val message: String) : GhibliUiState()
}

sealed class MovieDetailUiState {
    data object Loading : MovieDetailUiState()
    data class Success(val movie: GhibliMovie) : MovieDetailUiState()
    data class Error(val message: String) : MovieDetailUiState()
}