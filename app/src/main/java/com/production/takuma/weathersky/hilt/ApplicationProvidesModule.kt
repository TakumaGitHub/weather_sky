package com.production.takuma.weathersky.hilt

import android.content.Context
import androidx.room.Room
import com.production.takuma.weathersky.api.WeatherApiService
import com.production.takuma.weathersky.db.WeatherDao
import com.production.takuma.weathersky.db.WeatherDatabase
import com.production.takuma.weathersky.repository.WeatherRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * DI用ProvidesModule
 * @Inject が付いたプロパティや引数に提供する値の実体を定義
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationProvidesModule {

    /**
     * OkHttpClientの提供
     * @return OkHttpClientのインスタンス
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    /**
     * [WeatherApiService]の提供
     * @param okHttpClient
     * @return [WeatherApiService]のインスタンス
     */
    @Singleton
    @Provides
    fun provideWeatherApiService(okHttpClient: OkHttpClient): WeatherApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherApiService::class.java)
    }

    /**
     * [WeatherApiService]の提供
     * @param weatherApiService
     * @return [WeatherRepository]のインスタンス
     */
    @Provides
    fun provideWeatherRepository(
        weatherApiService: WeatherApiService,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepository(weatherApiService, weatherDao)
    }

    /**
     * [WeatherDatabase]の提供
     * @param context コンテキスト
     * @return [WeatherDatabase]のインスタンス
     */
    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db").build()

    /**
     * [weatherDao]の提供
     * @param db [WeatherDatabase]
     * @return [WeatherDao]のインスタンス
     */
    @Singleton
    @Provides
    fun provideWeatherDao(db: WeatherDatabase) = db.weatherDao()

    companion object {
        // ベースURL
        const val BASE_URL = "https://www.jma.go.jp/bosai/forecast/data/overview_forecast/"
    }
}