package com.example.parkingspot_finder.di_

import android.app.Application
import androidx.room.Room
import com.example.parkingspot_finder.data_.ParkingSpot_repo_impl
import com.example.parkingspot_finder.data_.ParkingspotDatabase
import com.example.parkingspot_finder.domain_.repository.ParkingspotRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideParkingSpotDatabase(app: Application): ParkingspotDatabase {
        return Room.databaseBuilder(
            app,
            ParkingspotDatabase::class.java,
            "parking_spots.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepository(db: ParkingspotDatabase): ParkingspotRepository{
        return ParkingSpot_repo_impl(db.dao)
    }
}
