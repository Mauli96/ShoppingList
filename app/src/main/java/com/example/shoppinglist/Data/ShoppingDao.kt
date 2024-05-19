package com.example.shoppinglist.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addList(newItem: ShoppingItem)

    @Query("Select * from `shopping-list`")
    abstract fun getAllList(): Flow<List<ShoppingItem>>

    @Update
    abstract suspend fun updateList(newItem: ShoppingItem)

    @Delete
    abstract suspend fun deleteList(newItem: ShoppingItem)

    @Query("Select * from `shopping-list` where id = :id")
    abstract fun getItemById(id: Long): Flow<ShoppingItem>
}