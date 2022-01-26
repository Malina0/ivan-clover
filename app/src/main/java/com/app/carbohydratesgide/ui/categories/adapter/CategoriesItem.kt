package com.app.carbohydratesgide.ui.categories.adapter

import com.app.carbohydratesgide.ui.categories.products.adapter.ProductsItem

data class CategoriesItem(
    val name: String,
    val image: Int,
    val product: ArrayList<ProductsItem>,
    val id: Int
)
