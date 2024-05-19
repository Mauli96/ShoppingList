package com.example.shoppinglist.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping-list")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo("name")
    var name: String,
    @ColumnInfo("quantity")
    var quantity: String
)

