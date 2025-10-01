package ph.alolodstudios.bookify.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ph.alolodstudios.bookify.common.Constants
import ph.alolodstudios.bookify.data.remote.OpenLibraryApi
import ph.alolodstudios.bookify.data.repositories.BookRepositoryImpl
import ph.alolodstudios.bookify.domain.repositories.BookRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenLibraryApi(): OpenLibraryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(api: OpenLibraryApi) : BookRepository {
        return BookRepositoryImpl(api)
    }
}