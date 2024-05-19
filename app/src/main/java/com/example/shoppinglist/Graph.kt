package com.example.shoppinglist

import com.example.shoppinglist.Data.ShoopingRepository
import com.example.shoppinglist.Data.ShoppingDataBase
import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var dataBase: ShoppingDataBase

    val shoppingRepository by lazy {
        ShoopingRepository(dataBase.shoppingDao())
    }

    fun provider(context: Context) {
        dataBase = Room.databaseBuilder(context, ShoppingDataBase::class.java, "shopping.db")
            .fallbackToDestructiveMigration().build()
    }
}