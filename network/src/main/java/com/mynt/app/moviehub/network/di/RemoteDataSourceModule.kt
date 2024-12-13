package com.mynt.app.moviehub.network.di

import com.mynt.app.moviehub.network.data.movie.MovieRemoteDataSource
import com.mynt.app.moviehub.network.data.movie.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindMovieRemoteDatasource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}
