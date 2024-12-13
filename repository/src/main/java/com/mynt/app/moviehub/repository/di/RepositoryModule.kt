package com.mynt.app.moviehub.repository.di

import com.mynt.app.moviehub.repository.movie.MovieRepository
import com.mynt.app.moviehub.repository.movie.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}
