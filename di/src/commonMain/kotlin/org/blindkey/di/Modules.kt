package org.blindkey.di

import androidx.room.RoomDatabase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.blindkey.data.TextRepositoryImpl
import org.blindkey.data.datasource.LocalDataSource
import org.blindkey.data.datasource.RemoteDataSource
import org.blindkey.data.local.TextDao
import org.blindkey.data.local.TextsDatabase
import org.blindkey.data.local.getDatabaseBuilder
import org.blindkey.data.local.getRoomDatabase
import org.blindkey.domain.repo.TextRepository
import org.blindkey.domain.usecase.GetRandomTextUseCase
import org.koin.dsl.module

private val dataModule = module {
    single<FirebaseFirestore> { Firebase.firestore }

    factory<RoomDatabase.Builder<TextsDatabase>> { getDatabaseBuilder() }
    single<TextsDatabase> { getRoomDatabase(get()) }
    single<TextDao> { get<TextsDatabase>().dao }

    factory<RemoteDataSource> { RemoteDataSource(get()) }
    factory<LocalDataSource> { LocalDataSource(get()) }
}

private val repoModule = module {
    includes(dataModule)
    single<TextRepository> { TextRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    includes(repoModule)
    factory<GetRandomTextUseCase> { GetRandomTextUseCase(get()) }
}