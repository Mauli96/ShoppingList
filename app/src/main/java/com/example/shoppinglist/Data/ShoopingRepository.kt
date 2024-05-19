package com.example.shoppinglist.Data

import kotlinx.coroutines.flow.Flow

class ShoopingRepository(private val shoppingDao: ShoppingDao) {

    fun getAllList(): Flow<List<ShoppingItem>> {
        return shoppingDao.getAllList()
    }

    suspend fun addList(newItem: ShoppingItem) {
        shoppingDao.addList(newItem)
    }

    suspend fun deleteList(newItem: ShoppingItem) {
        shoppingDao.deleteList(newItem)
    }

    suspend fun updateList(newItem: ShoppingItem) {
        shoppingDao.updateList(newItem)
    }

    fun getItemById(id: Long): Flow<ShoppingItem> {
        return shoppingDao.getItemById(id)
    }
}