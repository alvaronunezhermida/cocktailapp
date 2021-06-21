package com.alvaronunez.cocktailapp.data_implementation.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String
)