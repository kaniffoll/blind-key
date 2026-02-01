package org.blindkey.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.blindkey.data.TextRepositoryImpl
import org.blindkey.data.datasource.RemoteDataSource
import org.blindkey.domain.repo.TextRepository
import org.blindkey.domain.usecase.GetRandomTextUseCase
import org.koin.dsl.module

private val dataModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    factory<RemoteDataSource> { RemoteDataSource(get()) }
}

private val repoModule = module {
    includes(dataModule)
    single<TextRepository> { TextRepositoryImpl(get()) }
}

val useCaseModule = module {
    includes(repoModule)
    factory<GetRandomTextUseCase>{ GetRandomTextUseCase(get()) }
}