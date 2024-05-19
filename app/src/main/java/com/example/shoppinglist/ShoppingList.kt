package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonColors
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoppinglist.Data.ShoppingItem


@Composable
fun ShoppingList(
    viewModel: ShoppingListViewModel,
    navController: NavController
    ) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            AppBarView(title = "Shopping List")
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(20.dp),
                backgroundColor = colorResource(id = R.color.Button),
                shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 30)),
                onClick = {
                    viewModel.itemName = ""
                    viewModel.itemQuantity = ""
                    showDialog = true
                }
            ) {
                Row(
                    modifier = Modifier
                        .padding(18.dp),
                ){
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = Color.White,
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                    Text(
                        text = "New",
                        color = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->

        val shoppingList = viewModel.getAllList.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(shoppingList.value) { item ->
                ShoppingListItem(
                    item = item,
                    onDeleteClick = {
                        viewModel.deleteList(item)
                    },
                    onClick = {
                        val id = item.id
                        navController.navigate(Screen.AddScreen.route + "/$id")
                    }
                )
            }
        }
    }

    if(showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Button)),
                    ) {
                        Text(text = "Cancel")
                    }

                    Button(
                        onClick = {
                            if(viewModel.itemName.isNotBlank()) {
                                val newItem = ShoppingItem(
                                    name = viewModel.itemName,
                                    quantity = viewModel.itemQuantity
                                )
                                viewModel.addList(newItem)
                                showDialog = false
                                viewModel.itemName = ""
                                viewModel.itemQuantity = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Button)),
                    ) {
                        Text(text = "Add")
                    }
                } },
            title = {
                Text(
                    text = "Add Shopping Item",
                    fontWeight = FontWeight.SemiBold
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = viewModel.itemName,
                        onValueChange = {
                            viewModel.onItemNameChanged(it)
                        },
                        label = { Text(text = "Item Name")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = viewModel.itemQuantity,
                        onValueChange = {
                            viewModel.onItemQuantityChanged(it)
                        },
                        label = { Text(text = "Quantity")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
        )
    }

}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onDeleteClick: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
            .border(border = BorderStroke(2.dp, Color(0XFF018786)), shape = RoundedCornerShape(20)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.name,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "Qty: ${item.quantity}",
            modifier = Modifier
                .padding(8.dp)
        )
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}