package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoppinglist.Data.ShoppingItem
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun EditorScreen(
    id: Long,
    viewModel: ShoppingListViewModel,
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    if(id != 0L) {
        val shoppingItem = viewModel.getItemById(id).collectAsState(initial = ShoppingItem(0L, "", ""))
        viewModel.itemName = shoppingItem.value.name
        viewModel.itemQuantity = shoppingItem.value.quantity
    }
    else {
        viewModel.itemName = ""
        viewModel.itemQuantity = ""
    }

    Scaffold(
        topBar = {
            AppBarView(title = "Update List")
            { navController.navigateUp() }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = viewModel.itemName,
                onValueChange = {
                    viewModel.onItemNameChanged(it)
                },
                label = { Text(text = "Item Name", color = Color.Black) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = viewModel.itemQuantity,
                onValueChange = {
                    viewModel.onItemQuantityChanged(it)
                },
                label = { Text(text = "Quantity", color = Color.Black) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Button)),
                onClick = {
                    viewModel.updateList(
                        ShoppingItem(
                            id = id,
                            name = viewModel.itemName.trim(),
                            quantity = viewModel.itemQuantity.trim()
                        )
                    )
                    scope.launch {
                        navController.navigateUp()
                    }
                }) {
                Text(
                    text = "Update",
                    color = Color.White,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}