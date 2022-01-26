package com.app.carbohydratesgide.ui.categories.adapter

import com.app.carbohydratesgide.R
import com.app.carbohydratesgide.ui.categories.products.adapter.ProductInf

object CategoriesInf {
    val models = arrayListOf(
        CategoriesItem(
            "Мясо",
            R.drawable.test,
            ProductInf.one,
            0
        ),

        CategoriesItem(
            "Птица",
            R.drawable.ptica,
            ProductInf.ptica,
            1
        ),

        CategoriesItem(
            "Крупы и каши",
            R.drawable.crupi,
            ProductInf.crupi,
            2
        ),

        CategoriesItem(
            "Молочные продукты",
            R.drawable.moloch,
            ProductInf.moloch,
            3
        ),

        CategoriesItem(
            "Фрукты",
            R.drawable.fructi,
            ProductInf.fruct,
            4
        ),

        CategoriesItem(
            "Овощи и зелень",
            R.drawable.ovochi,
            ProductInf.ovochi,
            5
        ),

    )
}