package com.josuemartinez.openbrewery.database

import android.content.Context
import androidx.room.*
import com.josuemartinez.openbrewery.model.Brewery

@Database(version = 1, entities = [Brewery::class], exportSchema = false)
abstract class BreweryDatabase : RoomDatabase() {

    abstract val breweryDao: BreweryDao

    companion object{

        @Volatile
        private var INSTANCE: BreweryDatabase? = null

        fun getInstance(context: Context): BreweryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BreweryDatabase::class.java,
                        "brewery_listing_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}