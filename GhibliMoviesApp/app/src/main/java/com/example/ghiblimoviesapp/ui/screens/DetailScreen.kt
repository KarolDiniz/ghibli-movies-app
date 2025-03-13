package com.example.ghiblimoviesapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ghiblimoviesapp.data.model.GhibliMovie
import com.example.ghiblimoviesapp.ui.viewmodel.GhibliViewModel
import com.example.ghiblimoviesapp.ui.viewmodel.MovieDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: String,
    viewModel: GhibliViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val movieState by viewModel.selectedMovieState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Filme") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (movieState) {
                is MovieDetailUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                is MovieDetailUiState.Success -> {
                    val movie = (movieState as MovieDetailUiState.Success).movie
                    MovieDetail(movie = movie)
                }
                is MovieDetailUiState.Error -> {
                    val error = (movieState as MovieDetailUiState.Error).message
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDetail(movie: GhibliMovie) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Banner da imagem
        AsyncImage(
            model = movie.movie_banner,
            contentDescription = "Banner do filme ${movie.title}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = movie.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = movie.original_title,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                text = movie.original_title_romanised,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Poster
                AsyncImage(
                    model = movie.image,
                    contentDescription = "Poster do filme ${movie.title}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(225.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Diretor: ${movie.director}",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Produtor: ${movie.producer}",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Lançamento: ${movie.release_date}",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Duração: ${movie.running_time} min",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Pontuação: ${movie.rt_score}%",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF5722)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sinopse",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.description,
                fontSize = 16.sp
            )
        }
    }
}