package com.example.weather.model.repsitory.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.model.WeatherConverter
import com.example.weather.model.WeatherResponse

@Database(entities = [WeatherResponse::class], version = 1)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile private var instance: WeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weatherDatabase"
        ).build()
    }
}