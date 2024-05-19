package com.example.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.Data.ShoopingRepository
import com.example.shoppinglist.Data.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ShoppingListViewModel(
    private val shoppingRepository: ShoopingRepository = Graph.shoppingRepository
): ViewModel() {

    var itemName by mutableStateOf("")
    var itemQuantity by mutableStateOf("")

    fun onItemNameChanged(newString: String) {
        itemName = newString
    }
    fun onItemQuantityChanged(newString: String) {
        itemQuantity = newString
    }


    lateinit var getAllList: Flow<List<ShoppingItem>>

    init {
        viewModelScope.launch {
            getAllList = shoppingRepository.getAllList()
        }
    }

    fun addList(newItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingRepository.addList(newItem)
        }
    }

    fun deleteList(newItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingRepository.deleteList(newItem)
        }
    }

    fun updateList(newItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingRepository.updateList(newItem)
        }
    }

    fun getItemById(id: Long): Flow<ShoppingItem> {
        return shoppingRepository.getItemById(id)
    }
}