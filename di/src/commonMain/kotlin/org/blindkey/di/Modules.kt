package org.blindkey.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.blindkey.data.AppSettingsImpl
import org.blindkey.data.TextRepositoryImpl
import org.blindkey.data.UrlOpenerImpl
import org.blindkey.data.local.LocalDataSource
import org.blindkey.data.remote.RemoteDataSource
import org.blindkey.data.datastore.createDataStore
import org.blindkey.data.local.TextDao
import org.blindkey.data.local.TextsDatabase
import org.blindkey.data.local.getDatabaseBuilder
import org.blindkey.data.local.getRoomDatabase
import org.blindkey.domain.repo.TextRepository
import org.blindkey.domain.settings.AppSettings
import org.blindkey.domain.uri.UrlOpener
import org.blindkey.domain.usecase.AddTextUseCase
import org.blindkey.domain.usecase.GetRandomTextUseCase
import org.blindkey.domain.usecase.GetTestParamUseCase
import org.blindkey.domain.usecase.OpenUrlUseCase
import org.blindkey.domain.usecase.SaveTestParamUseCase
import org.koin.dsl.module

private val dataModule = module {
    single<FirebaseFirestore> { Firebase.firestore }

    factory<RoomDatabase.Builder<TextsDatabase>> { getDatabaseBuilder() }
    single<TextsDatabase> { getRoomDatabase(get()) }
    single<TextDao> { get<TextsDatabase>().dao }

    factory<RemoteDataSource> { RemoteDataSource(get()) }
    factory<LocalDataSource> { LocalDataSource(get()) }

    single<DataStore<Preferences>> { createDataStore() }
    factory<UrlOpener> { UrlOpenerImpl() }
}

private val repoModule = module {
    includes(dataModule)
    single<TextRepository> { TextRepositoryImpl(get(), get()) }
    single<AppSettings> { AppSettingsImpl(get(), get(), get()) }
}

val useCaseModule = module {
    includes(repoModule)
    factory<GetRandomTextUseCase> { GetRandomTextUseCase(get()) }
    factory<AddTextUseCase> { AddTextUseCase(get()) }
    factory<GetTestParamUseCase> { GetTestParamUseCase(get()) }
    factory<SaveTestParamUseCase> { SaveTestParamUseCase(get()) }
    factory<OpenUrlUseCase> { OpenUrlUseCase(get()) }
}