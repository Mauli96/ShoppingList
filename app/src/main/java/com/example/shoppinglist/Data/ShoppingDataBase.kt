package com.example.shoppinglist.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 2,
    exportSchema = false
)

abstract class ShoppingDataBase: RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}