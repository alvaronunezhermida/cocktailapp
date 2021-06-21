package com.alvaronunez.cocktailapp.data_implementation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alvaronunez.cocktailapp.data_implementation.database.daos.IngredientsDao
import com.alvaronunez.cocktailapp.data_implementation.database.entities.IngredientEntity

@Database(entities = [IngredientEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "cocktails-db"
        ).allowMainThreadQueries()
            .build()
    }

    abstract fun ingredientsDao(): IngredientsDao

}