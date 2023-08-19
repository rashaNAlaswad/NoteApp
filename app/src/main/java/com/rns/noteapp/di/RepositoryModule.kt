package com.rns.noteapp.di

import com.rns.noteapp.data.dao.NoteDao
import com.rns.noteapp.data.repository.NoteRepository
import com.rns.noteapp.data.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(
        dao: NoteDao,
    ): NoteRepository {
        return NoteRepositoryImpl(dao)
    }
}