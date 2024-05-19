package com.example.shoppinglist

import android.app.Application

class ShoppingListApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provider(this)
    }
}